import java.util.*;
import java.io.*;

class ornithology{

    public static void main (String [] args) {

        myscanner scanner = new myscanner();

        int s = scanner.nextInt();
        int n = scanner.nextInt();

        ArrayList<point> [] paths = new ArrayList [s];
        Arrays.fill(paths, new ArrayList<>());

        for (int i = 0; i < s; i++){
            int k = scanner.nextInt();
            for (int j = 0; j < k; j++){

                int x = scanner.nextInt();
                int y = scanner.nextInt();
                int z = scanner.nextInt();
                point p = new point(x, y, z);
                paths[i].add(p);
            }
        }
        int max = 0;
        int min = n;
        int ind = 0;
        for (int i = 0; i < s; i++) {
            if (i != ind){
                int dist = pathdist(paths[i], paths[ind]);
                if(dist > max){
                    max = dist;
                }
            }
        }
        System.out.println(max);


    }

    static int pathdist(ArrayList<point> a, ArrayList<point> b) {
        int [][] x = new int[a.size()][b.size()];

        // initialize all values to -1
        for (int i = 0; i < a.size(); i++) {
            Arrays.fill(x[i], -1);
        }
        for(int i = 0; i< a.size(); i++){
            for(int j = 0; j< b.size(); j++){

                int d = a.get(i).dist(b.get(j));

                if (i == 0 && j == 0)
                    x[i][j] = d;
                else if (i > 0 && j == 0)
                    x[i][j] = Math.max(x[i-1][0], d);
                else if (i == 0 && j > 0)
                    x[i][j] = Math.max(x[0][j-1], d);
                else if (i > 0 && j > 0) {
                    x[i][j] = Math.max(Math.min(Math.min(x[i - 1][ j], x[i - 1][ j - 1]), x[i][j - 1]), d);
                } else {
                    x[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        return x[a.size() -1][b.size()-1];
    }
}

class point{
    int x;
    int y;
    int z;

    public point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int dist(point p){
        return ((x-p.getX()*(x-p.getX()))+(y-p.getY())*(y-p.getY())+(z-p.getZ())*(z-p.getZ()));
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

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
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
