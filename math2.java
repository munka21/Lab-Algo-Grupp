import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class math2{



    public static void main(String [] args) throws NumberFormatException, IOException {
        BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
        int n =Integer.parseInt(bi.readLine());
        for (int i = 0; i < n; i++) {
            String[] line= bi.readLine().split(" ");

            long u = Long.parseLong(line[0]);
            long v = Long.parseLong(line[1]);
            long w = Long.parseLong(line[2]);
            boolean done = false;

            ArrayList<Long> factors= allFactors(v, Math.min(v,w));
            //ArrayList<Long> factors= allFactors(v, Math.min(v));// guarantees that x*y*z=V
            //go through it backwards getting possibly negative solutions



            for(int j=0; j<factors.size()-2;j++) {
                long x=factors.get(j);
                long xs = x*x;

                double upperBoundY= Math.sqrt(((double)v)/(double)x);
                for(int k=j+1; k<factors.size()-1;k++) {
                    long y=factors.get(k);
                    if(y>upperBoundY) {
                        break;
                    }
//                    while(y<=x) {//guarantees for new x that y is greater than x
//                        k++;
//                        y=factors.get(k);
//                    }
                    long z=u-x-y;

                    if(y<z) {
                        if ((xs + y * y + (z) * (z) == w)&& (x*y*z==v) )  {
                            System.out.println(x + " " + y + " " + z);
                            done = true;
                            break;
                        }
                    }else {
                        k=factors.size();//break out of inner loop
                    }
                }

            }
            if (!done) {
                System.out.println("empty set");
            }


        }
    }



    public static ArrayList<Long> allFactors(long a, long upperBound) {
        //long time = System.nanoTime();
        long upperlimit = (long)(Math.sqrt(upperBound));

        ArrayList<Long> factors = new ArrayList<Long>();

        for(long i=1;i <= upperlimit; i+= 1){
            int s = factors.size();
            if(a%i == 0){
                factors.add(3*(s/4), i);
                factors.add((s/4),-i);
                if(i != a/i){
                    long temp=a/i;
                    factors.add(3*(s/4)+2,temp);
                    factors.add(s/4,-temp);
                }
            }
        }
        //System.out.println("AL time:\t" + (System.nanoTime() - time));
        return factors;
    }

}
