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
            boolean done = false;

            long max = Math.min(w , v);
            max = (long)Math.sqrt(max);
            for(long x=-max; x<max; x++) {

                while(x == 0||v%x != 0){x++;}

                System.out.println(x);
                long y = mitternachtsformel(u, x, -u + x, v / x);

                if (y != 0 && y > x) {
                    long z = u - x - y;
                    if (z * z + y * y + x * x == w ) {
                        //log.write((x + " " + y + " " + z + "\n").getBytes());
                        System.out.println(x+" "+y+ " "+z);
                        done = true;
                        break;
                    }
                }
            }
            if (!done) {
                System.out.println("empty set");
            }
            System.out.flush();
        }

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
