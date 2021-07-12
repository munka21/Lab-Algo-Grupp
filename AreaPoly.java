import java.util.*;
import java.io.*;

class AreaPoly
{
    public static void main(String [] args) {

        myscanner scanner = new myscanner();

        int n = scanner.nextInt();
        int maxx = 0;
        int minx = 10;
        int maxy = 0;
        int miny = 10;
        int kgv = 10;

        Polygon [] poly = new Polygon[n];
        for(int i = 0; i< n; i++){
            int l = scanner.nextInt();
            poly[i] = new Polygon(l);
            for(int k = 0; k< l; k++){
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                if(x < minx){
                    minx = x;
                }
                if(x > maxx){
                    maxx = x;
                }
                if(y < miny){
                    miny = y;
                }
                if(y > maxy){
                    maxy = y;
                }

                poly[i].addPoint(new Point(x, y,kgv));
            }
        }

        int border = 0;
        int inside = 0;
        for(int i = minx; i<= kgv*maxx; i++){
            for(int j = miny; j<= kgv*maxy; j++){
                Point x = new Point(i, j, 1);
                int pos = 1;
                for(int k = 0; k< n; k++){
                    pos = Math.min(poly[k].pointToPolygon(x), pos);
                    if(pos == -1){
                        break;
                    }
                }
                if(pos == 0){
                    border++;
                }
                else if (pos == 1){
                    inside++;
                }
            }
        }
        //System.out.println(border+ " "+ inside);
        double area = inside + (((double)border)/2) -1;
        //System.out.println(area);
        area = (area)/(kgv*kgv);
        System.out.println(area);



    }
}
class Polygon{
    Point [] points;
    int ind = 0;
    Polygon(int size){
        points = new Point[size];
    }
    void addPoint(Point p){
        points[ind]=p;
        ind++;
    }
    int pointToPolygon(Point x){
        int t = -1;
        for(int i = 0; i< points.length; i++){
            Point p = points[i];
            Point q = points[0];
            if(i<points.length-1) {
                q = points[i + 1];
            }

            t = t*test(p, q, x);

            if(t == 0){
                break;
            }
        }
        //System.out.println(t);
        return t;
    }
    int test(Point p, Point q, Point x){
        if(p.getY()==x.getY() && q.getY()==x.getY()){
            if((p.getX()<=x.getX() && x.getX()<=q.getX())||(p.getX()>=x.getX() && x.getX()>=q.getX())){
                return 0;
            }
            else{
                return 1;
            }
        }
        if(x.getX()==p.getX()&&x.getY()==p.getY()){
            return 0;
        }
        if(q.getY()<p.getY()){
            Point temp = p;
            p = q;
            q = temp;
        }
        if(x.getY()<=p.getY() || q.getY()<x.getY()){
            //System.out.println("x");
            return 1;


        }
        int k = (p.getX()-x.getX())*(q.getY()-x.getY())-(p.getY()-x.getY())*(q.getX()-x.getX());
        if(k > 0){
            return -1;
        }
        else if(k == 0){

            return 0;
        }
        else{
            return 1;
        }
    }
}

class Point{
    int x;
    int y;

    Point(int xn, int yn, int i){
        this.x = i*xn;
        this.y = i*yn;
    }
    Point(int xn, int yn){
        this.x = xn;
        this.y = yn;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "(" + x +
                ", " + y +
                ")";
    }
}

class myscanner{
    BufferedReader br;
    StringTokenizer st;

    public myscanner() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }


    int nextInt() {
        return Integer.parseInt(next());
    }
}
