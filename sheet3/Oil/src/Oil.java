import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeSet;


public class Oil {
	public int n;
	public ArrayList<Integer> parcels;
	
	public static void main(String[] args)  {
		
		Oil me = new Oil();
		try {
			me.doMyThing();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	private void doMyThing() throws NumberFormatException, IOException {
		// read in 
		BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
		this.n=Integer.parseInt(bi.readLine());
		if(n>0) {
			String[] profit_entries= bi.readLine().split(" ");
			this.parcels= new ArrayList<Integer>(n*n);
			for(String s: profit_entries) {
				parcels.add(Integer.parseInt(s));
			}
			System.out.println(calculateMaxProfit());
		}else {
			System.out.println("0");
		}
		
		
		
	}
	
	public int calculateMaxProfit() {
		int i1=findRowOfUppermostPositiveParcel();
		if(i1==this.n) {
			return 0;
		}
		int j1= findColumnOfLeftmostPositiveParcel();
		int i2=findRowOfLowermostPositiveParcel();
		int j2=findColumnOfRightmostPositiveParcel();
		

		TreeSet<Rectangle> queue=new TreeSet<Rectangle>();
		Rectangle maxRect=new Rectangle(i1,i2,j1,j2, calculateProfit(i1,i2,j1,j2), calculatePotential(i1,i2,j1,j2));
		queue.add(maxRect);
		while(!queue.isEmpty()) {
			Rectangle temp= queue.last();
			queue.remove(temp);
			if(temp.profit>maxRect.profit) {
				maxRect=temp;
			}
			if(temp.potential>maxRect.profit) {//otherwise it can never get better
				ArrayList<Rectangle> recTemp=temp.cutOfSlices();
				for(Rectangle r: recTemp) {
					if(r.potential>maxRect.profit) {
						queue.add(r);
					}
				}
			}
			
		}
		return maxRect.profit;
	}
	
	public int transformIndex(int i, int j) {
		return i*this.n+j;
	}
	
	public int findRowOfUppermostPositiveParcel() {
		for(int i=0; i<n;i++) {
			for(int j=0; j<n; j++) {
				if(this.parcels.get(transformIndex(i,j))>0) {
					return i;
				}
			}
		}
		return n;//meaning there does not exist any positiv value.
	}
	
	public int findColumnOfLeftmostPositiveParcel() {
		for (int j=0; j<n; j++){
			for(int i=0; i<n;i++) {
				if(this.parcels.get(transformIndex(i,j))>0) {
					return j;
				}
			}
		}
		return n;//meaning there does not exist any positiv value.
	}
	public int findColumnOfRightmostPositiveParcel() {
		for(int j=n-1; j>=0;j--) {
			for(int i=0; i<n; i++) {
				if(this.parcels.get(transformIndex(i,j))>0) {
					return j;
				}
			}
		}
		return n;//meaning there does not exist any positiv value.
	}
	
	
	
	public int findRowOfLowermostPositiveParcel() {
		for(int i=n-1; i>=0;i--) {
			for(int j=0; j<n; j++) {
				if(this.parcels.get(transformIndex(i,j))>0) {
					return i;
				}
			}
		}
		return n;//meaning there does not exist any positiv value.
	}
	public int calculateProfit(int i1, int j1, int i2, int j2) {
		int profit=0;
		for(int i=i1; i<=i2;i++) {
			for(int j=j1; j<=j2;j++) {
				profit+= this.parcels.get(transformIndex(i,j));
			}
		}
		return profit;
	}
	
	public int calculatePotential(int i1, int j1, int i2, int j2) {
		int potential=0;
		for(int i=i1; i<=i2;i++) {
			for(int j=j1; j<=j2;j++) {
				int temp= this.parcels.get(transformIndex(i,j));
				if(temp>0) {
					potential+=temp;
				}
			}
		}
		return potential;
	}
	
	public class Rectangle implements Comparable<Rectangle> {
		private int upperLeftI;
		private int upperLeftJ;
		private int lowerRightI;
		private int lowerRightJ;
		private int profit;
		private int potential;
		
		Rectangle(int i1, int j1, int i2, int j2, int profit, int potential){
			this.upperLeftI=i1;
			this.upperLeftJ=j1;
			this.lowerRightI=i2;
			this.lowerRightJ=j2;
			this.profit=profit;
			this.potential=potential;
			
		}
		
		public ArrayList<Rectangle> cutOfSlices(){
			ArrayList<Rectangle> retval= new ArrayList<Rectangle>(8);
			int potentialTemp=0;
			int profitTemp=0;
			if(this.lowerRightJ-this.upperLeftJ>0) {
				//cut of leftmost column
				profitTemp=calculateProfit(this.upperLeftI, this.upperLeftJ, this.lowerRightI, this.upperLeftJ);
				potentialTemp=calculatePotential(this.upperLeftI, this.upperLeftJ, this.lowerRightI, this.upperLeftJ);
				
				if(profitTemp>0) {
					retval.add(new Rectangle(this.upperLeftI, this.upperLeftJ, this.lowerRightI, this.upperLeftJ, profitTemp, potentialTemp));
				}
				retval.add(new Rectangle(this.upperLeftI, this.upperLeftJ+1, this.lowerRightI, this.lowerRightJ, this.profit-profitTemp, this.potential-potentialTemp));
				//cut off rightmost column
				profitTemp=calculateProfit(this.upperLeftI, this.lowerRightJ, this.lowerRightI, this.lowerRightJ);
				potentialTemp=calculatePotential(this.upperLeftI, this.lowerRightJ, this.lowerRightI, this.lowerRightJ);
				
				if(profitTemp>0) {
					retval.add(new Rectangle(this.upperLeftI, this.lowerRightJ, this.lowerRightI, this.lowerRightJ, profitTemp, potentialTemp));
				}
				retval.add(new Rectangle(this.upperLeftI, this.upperLeftJ, this.lowerRightI, this.lowerRightJ-1, this.profit-profitTemp, this.potential-potentialTemp));
				
			}
			
			if(this.lowerRightI-this.upperLeftI>0) {
				//cut off upper row
				profitTemp=calculateProfit(this.upperLeftI, this.upperLeftJ, this.upperLeftI, this.lowerRightJ);
				potentialTemp=calculatePotential(this.upperLeftI, this.upperLeftJ, this.upperLeftI, this.lowerRightJ);
				
				if(profitTemp>0) {
					retval.add(new Rectangle(this.upperLeftI, this.upperLeftJ, this.upperLeftI, this.lowerRightJ, profitTemp, potentialTemp));
				}
				retval.add(new Rectangle(this.upperLeftI+1, this.upperLeftJ, this.lowerRightI, this.lowerRightJ, this.profit-profitTemp, this.potential-potentialTemp));
				
				//cut off lower row
				profitTemp=calculateProfit(this.lowerRightI, this.upperLeftJ, this.lowerRightI, this.lowerRightJ);
				potentialTemp=calculatePotential(this.lowerRightI, this.upperLeftJ, this.lowerRightI, this.lowerRightJ);
				
				if(profitTemp>0) {
					retval.add(new Rectangle(this.lowerRightI, this.upperLeftJ, this.lowerRightI, this.lowerRightJ, profitTemp, potentialTemp));
				}
				retval.add(new Rectangle(this.upperLeftI, this.upperLeftJ, this.lowerRightI-1, this.lowerRightJ, this.profit-profitTemp, this.potential-potentialTemp));
				
			}
			
			return retval;
		}
		
		@Override
		public int compareTo(Rectangle r) {
			return this.profit-r.profit;
		}
	}
	

}
