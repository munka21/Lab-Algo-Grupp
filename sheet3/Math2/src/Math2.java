import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Math2{



    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            long u = scanner.nextLong();
            long v = scanner.nextLong();
            long w = scanner.nextLong();
            boolean done = false;
            
            ArrayList<Long> factors= allFactors(v, Math.min(v,w)); // guarantees that x*y*z=V
            //go through it backwards getting possibly negative solutions
           
            
             
            for(int j=0; j<factors.size()-2;j++) {
            	long x=factors.get(j);
            	
            	double upperBoundY= Math.sqrt(((double)v)/(double)x);
            	for(int k=j+1; k<factors.size()-1;k++) {
            		long y=factors.get(k);
            		if(y>upperBoundY) {
            			break;
            		}
            		while(y<=x) {//guarantees for new x that y is greater than x
            			k++;
            			y=factors.get(k);
            		}
            		long z=u-x-y;
            		
            		if(y<z) {
            			if ((x * x + y * y + (z) * (z) == w)&& (x*y*z==v) )  {
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

        long upperlimit = (long)(Math.sqrt(upperBound));
        ArrayList<Long> factors = new ArrayList<Long>();
        for(long i=1;i <= upperlimit; i+= 1){
            if(a%i == 0){
                factors.add(i);
                factors.add(-i);
                if(i != a/i){
                	long temp=a/i;
                    factors.add(temp);
                    factors.add(-temp);
                }
            }
        }
        Collections.sort(factors);
        return factors;
    }

}