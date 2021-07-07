import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class math3{

    public static void main(String [] args){

        FileOutputStream fdout = new FileOutputStream(FileDescriptor.out);
        BufferedOutputStream outBuf = new BufferedOutputStream(fdout, 2048);
        PrintStream outStream = new PrintStream(outBuf, false);
        System.setOut(outStream);

        myscanner scanner = new myscanner();

        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {

            long u = scanner.nextLong();
            long v = scanner.nextLong();
            long w = scanner.nextLong();

            long high = (long) Math.sqrt(w);;
            long low = - (long) Math.sqrt(w);
            long x = (long)(low + ((high - low)/2));
            while (high - low > 1){

                x = (long)(low + ((high - low)/2));

                if (function(x, u, v, w) > 0) {
                    high = x;
                }
                else if (function(x, u, v, w) < 0) {
                    low = x;
                }
                else {
                    low = x;
                    high = x;
                }
            }

            if(low == 0){
                System.out.println("empty set");
            }
            else{
                long erg [] = new long [3];
                erg[0] = low;
                erg[1] = mitternachtsformel(u, low, -u + x, v / low);
                if(erg[1] == 0){
                    System.out.println("empty set");
                }
                else{
                    erg[2] = u-erg[0]-erg[1];

                    Arrays.sort(erg);

                    System.out.println(erg[0]+ " "+ erg[1]+ " "+ erg[2]);
                }

            }
        }
        System.out.flush();

    }

    public static long function(long x, long u, long v, long w){
        return x*x*x-u*x*x+((u*u-w)/2)*x-v;
    }
    public static long mitternachtsformel(long u,long x, long b , long c){

        double dis = Math.sqrt(b*b-4*c);
        long disk = (long)dis;
        //System.out.println(disk+ " "+ dis);
        if((dis -(double)b)/2 == (-b + disk)/2) {
            //System.out.println(disk);
            long erg1 = (-b + disk)/2;
            long erg2 = (-b - disk)/2;
            return Math.min(erg2, u - x - erg1);
        }
        else{
            return 0;
        }

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

    long nextLong() {
        return Long.parseLong(next());
    }
}
