import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.TreeSet;

public class KMeans2V2 {
	public static void mymain(String[] args, OutputStream fdout) throws NumberFormatException, IOException {
		//adjustions to outputbuffer
	    // Setze Puffergröße auf 2048 byte
		try( BufferedOutputStream outBuf = new BufferedOutputStream(fdout, 2048);
			    // false -> nicht automatisch den Puffer bei \n leeren
			    PrintStream outStream = new PrintStream(outBuf, false);){
			
		
	   
	    System.setOut(outStream); 
		
		BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
		String[] firstLine= bi.readLine().split(" ");
		int numberOfCenters=Integer.parseInt(firstLine[0]);
		int numberOfQueryPoints=Integer.parseInt(firstLine[1]);
		int r=Integer.parseInt(firstLine[2].replace(".",""))*10;
		TreeSet<Integer> centers=new TreeSet<Integer>();
		for (int i=1; i<=numberOfCenters;i++) {
			String temp=bi.readLine().replace(".","");
			centers.add(Integer.parseInt(temp)*10);
		}
		for(int i=0; i< numberOfQueryPoints;i++) {
			String temp=bi.readLine().replace(".","");
			int q=Integer.parseInt(temp)*10+1;
			
				Integer higherCenter= centers.higher(q);
				Integer lowerCenter=centers.lower(q);
				if(lowerCenter!=null && higherCenter!=null){
					if((higherCenter-q+1)<=r&& (q-1-lowerCenter)<=r){
						if(higherCenter-q+1 < q-1-lowerCenter) {
							System.out.println(java.lang.String.format("%.02f", (double)higherCenter/1000));
						} else {
							System.out.println(java.lang.String.format("%.02f", (double)lowerCenter/1000));
						}
					} else if((higherCenter-q+1)<=r) {

						System.out.println(java.lang.String.format("%.02f", (double)higherCenter/1000));
					} else if((q-1-lowerCenter)<=r) {

						System.out.println(java.lang.String.format("%.02f", (double)lowerCenter/1000));
					} else {
						System.out.println("none in range");
					}
				}
				else if(lowerCenter!=null&& (q-lowerCenter)<=r) {
					System.out.println(java.lang.String.format("%.02f", (double)lowerCenter/1000));
				} else if (higherCenter!=null&& (higherCenter-q)<=r) {
					System.out.println(java.lang.String.format("%.02f", (double)higherCenter/1000));
				}else {
					System.out.println("none in range");
				}
				
			}
			
		
		
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		FileOutputStream fdout = new FileOutputStream(FileDescriptor.out); 
		mymain(args,fdout);
		
	}
		
}
