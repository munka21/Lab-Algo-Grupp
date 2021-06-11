import java.util.*;
import java.io.*;

class skyline{

    public static void main (String [] args) {

        myscanner scanner = new myscanner();

        int n = scanner.nextInt();

        LinkedList <building> [] array = new LinkedList[n*n];

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++ ){
            for (int j = 0; j < n; j++){
                int h = scanner.nextInt();

                building b = new building(i, j, h);

                if(!(array[h]== null)){
                    Iterator <building> it = array[h].iterator();
                    while (it.hasNext()) {
                        building next = it.next();
                        //System.out.println(next);
                        if (next.dist(b)<min) {
                           min = next.dist(b);
                        }
                    }
//                    for(int k = 0; k < array[h].size(); k++){
//                        if (array[h].get(k).dist(b)<min){
//                            min = array[h].get(k).dist(b);
//
//                        }
//                    }
                    array[h].add(b);
                }
                else{
                    array[h] = new LinkedList<building>();
                    array[h].add(b);
                }

            }
        }
        System.out.println(min);
    }
}


class building{

    int x;
    int y;
    int height;

    public building(int x, int y, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
    }

    public int dist(building b){
        return Math.abs(x - b.getX())+Math.abs(y-b.getY());

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "building{" +
                "x=" + x +
                ", y=" + y +
                ", height=" + height +
                '}';
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
