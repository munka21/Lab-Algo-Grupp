import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;


public class Oil {
	public int n;
	public ArrayList<Integer> parcels;
	public HashMap<Rectangle,Rectangle> allRectanglesSeen;
	
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
			allRectanglesSeen= new HashMap<Rectangle,Rectangle>();
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
		Rectangle maxRect=new Rectangle(i1,j1,i2,j2, calculateProfit(i1,j1,i2,j2), calculatePotential(i1,j1,i2,j2));
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
		
		Rectangle(int i1, int j1, int i2, int j2){
			this.upperLeftI=i1;
			this.upperLeftJ=j1;
			this.lowerRightI=i2;
			this.lowerRightJ=j2;
			
		}
		public void setProfit(int profit) {
			this.profit=profit;
		}
		
		public void setPotential(int potential) {
			this.potential=potential;
		}
		
		public ArrayList<Rectangle> cutOfSlices(){
			ArrayList<Rectangle> retval= new ArrayList<Rectangle>(8);
			int potentialTemp=0;
			int profitTemp=0;
			if(this.lowerRightJ-this.upperLeftJ>0) {
				//cut of leftmost column
				Rectangle tempRect= new Rectangle(this.upperLeftI, this.upperLeftJ, this.lowerRightI, this.upperLeftJ);
				if(!allRectanglesSeen.containsKey(tempRect)) {// to avoid looking at the same Rectangle multiple times
					//TODO idea to improve: check in allRectanglesSeen for the vector with one component more and substract that one parcel.
					//check if one field longer Vector has already been looked at:
					Rectangle leftColumnUp=new Rectangle(this.upperLeftI-1, this.upperLeftJ, this.lowerRightI, this.upperLeftJ);
					Rectangle leftColumnDown=new Rectangle(this.upperLeftI, this.upperLeftJ, this.lowerRightI+1, this.upperLeftJ);
					if(allRectanglesSeen.containsKey(leftColumnUp)) {
						profitTemp= allRectanglesSeen.get(leftColumnUp).profit-parcels.get(transformIndex(this.upperLeftI-1, this.upperLeftJ));
						if(parcels.get(transformIndex(this.upperLeftI-1, this.upperLeftJ))>0) {
							potentialTemp= allRectanglesSeen.get(leftColumnUp).potential-parcels.get(transformIndex(this.upperLeftI-1, this.upperLeftJ));
							
						}else {
							potentialTemp= allRectanglesSeen.get(leftColumnUp).potential;
						}
					}else if(allRectanglesSeen.containsKey(leftColumnDown)){
						profitTemp= allRectanglesSeen.get(leftColumnDown).profit-parcels.get(transformIndex(this.lowerRightI+1, this.upperLeftJ));
						if(parcels.get(transformIndex(this.lowerRightI+1, this.upperLeftJ))>0) {
							potentialTemp= allRectanglesSeen.get(leftColumnDown).potential-parcels.get(transformIndex(this.lowerRightI+1, this.upperLeftJ));
							
						}else {
							potentialTemp= allRectanglesSeen.get(leftColumnDown).potential;
						}
					}else {
						profitTemp=calculateProfit(this.upperLeftI, this.upperLeftJ, this.lowerRightI, this.upperLeftJ);
						potentialTemp=calculatePotential(this.upperLeftI, this.upperLeftJ, this.lowerRightI, this.upperLeftJ);
						
					}
					tempRect.setProfit(profitTemp);
					tempRect.setPotential(potentialTemp);
					allRectanglesSeen.put(tempRect,tempRect);
					if(profitTemp>0) {
						retval.add(tempRect);
					}
					Rectangle tempRectInverse= new Rectangle(this.upperLeftI, this.upperLeftJ+1, this.lowerRightI, this.lowerRightJ, this.profit-profitTemp, this.potential-potentialTemp);
					allRectanglesSeen.put(tempRectInverse, tempRectInverse);
					retval.add(tempRectInverse);
					
				}
				
				//cut off rightmost column
				tempRect= new Rectangle (this.upperLeftI, this.lowerRightJ, this.lowerRightI, this.lowerRightJ);
				if(!allRectanglesSeen.containsKey(tempRect)) {
					
					Rectangle rightColumnUp=new Rectangle(this.upperLeftI-1, this.lowerRightJ, this.lowerRightI, this.lowerRightJ);
					Rectangle rightColumnDown=new Rectangle(this.upperLeftI, this.lowerRightJ, this.lowerRightI+1, this.lowerRightJ);
					if(allRectanglesSeen.containsKey(rightColumnUp)) {
						profitTemp= allRectanglesSeen.get(rightColumnUp).profit-parcels.get(transformIndex(this.upperLeftI-1, this.lowerRightJ));
						if(parcels.get(transformIndex(this.upperLeftI-1, this.lowerRightJ))>0) {
							potentialTemp= allRectanglesSeen.get(rightColumnUp).potential-parcels.get(transformIndex(this.upperLeftI-1, this.lowerRightJ));
							
						}else {
							potentialTemp= allRectanglesSeen.get(rightColumnUp).potential;
						}
					}else if(allRectanglesSeen.containsKey(rightColumnDown)){
						profitTemp= allRectanglesSeen.get(rightColumnDown).profit-parcels.get(transformIndex(this.lowerRightI+1, this.lowerRightJ));
						if(parcels.get(transformIndex(this.lowerRightI+1, this.lowerRightJ))>0) {
							potentialTemp= allRectanglesSeen.get(rightColumnDown).potential-parcels.get(transformIndex(this.lowerRightI+1, this.lowerRightJ));
							
						}else {
							potentialTemp= allRectanglesSeen.get(rightColumnDown).potential;
						}
					}else {
						profitTemp=calculateProfit(this.upperLeftI, this.lowerRightJ, this.lowerRightI, this.lowerRightJ);
						potentialTemp=calculatePotential(this.upperLeftI, this.lowerRightJ, this.lowerRightI, this.lowerRightJ);
						
					}
					tempRect.setProfit(profitTemp);
					tempRect.setPotential(potentialTemp);
					allRectanglesSeen.put(tempRect, tempRect);
					if(profitTemp>0) {
						retval.add(tempRect);
					}
					Rectangle tempRectInverse= new Rectangle(this.upperLeftI, this.upperLeftJ, this.lowerRightI, this.lowerRightJ-1, this.profit-profitTemp, this.potential-potentialTemp);
					allRectanglesSeen.put(tempRectInverse, tempRectInverse);
					retval.add(tempRectInverse);
					
				}
				
			}
			
			if(this.lowerRightI-this.upperLeftI>0) {
				//cut off upper row
				Rectangle tempRect= new Rectangle(this.upperLeftI, this.upperLeftJ, this.upperLeftI, this.lowerRightJ);
				
				if(!allRectanglesSeen.containsKey(tempRect)) {
					Rectangle upperRowLeft=new Rectangle(this.upperLeftI, this.upperLeftJ-1, this.upperLeftI, this.lowerRightJ);
					Rectangle upperRowRight=new Rectangle(this.upperLeftI, this.upperLeftJ, this.upperLeftI, this.lowerRightJ+1);
					
					if(allRectanglesSeen.containsKey(upperRowLeft)) {
						profitTemp=allRectanglesSeen.get(upperRowLeft).profit-parcels.get(transformIndex(this.upperLeftI, this.upperLeftJ-1));
						if(parcels.get(transformIndex(this.upperLeftI, this.upperLeftJ-1))>0){
							potentialTemp=allRectanglesSeen.get(upperRowLeft).potential-parcels.get(transformIndex(this.upperLeftI, this.upperLeftJ-1));
							
						}else {
							potentialTemp=allRectanglesSeen.get(upperRowLeft).potential;
						}
					} else if(allRectanglesSeen.containsKey(upperRowRight)) {
						profitTemp=allRectanglesSeen.get(upperRowRight).profit-parcels.get(transformIndex(this.upperLeftI, this.lowerRightJ+1));
						if(parcels.get(transformIndex(this.upperLeftI, this.lowerRightJ+1))>0){
							potentialTemp=allRectanglesSeen.get(upperRowRight).potential-parcels.get(transformIndex(this.upperLeftI, this.lowerRightJ+1));
							
						}else {
							potentialTemp=allRectanglesSeen.get(upperRowRight).potential;
						}
					} else {
						profitTemp=calculateProfit(this.upperLeftI, this.upperLeftJ, this.upperLeftI, this.lowerRightJ);
						potentialTemp=calculatePotential(this.upperLeftI, this.upperLeftJ, this.upperLeftI, this.lowerRightJ);
					
						
					}
					tempRect.setProfit(profitTemp);
					tempRect.setPotential(potentialTemp);
					allRectanglesSeen.put(tempRect, tempRect);
					if(profitTemp>0) {
						retval.add(tempRect);
					}
					Rectangle tempRectInverse= new Rectangle(this.upperLeftI+1, this.upperLeftJ, this.lowerRightI, this.lowerRightJ, this.profit-profitTemp, this.potential-potentialTemp);
					allRectanglesSeen.put(tempRectInverse, tempRectInverse);
					retval.add(tempRectInverse);
					
				}
				
				//cut off lower row
				tempRect=new Rectangle(this.lowerRightI, this.upperLeftJ, this.lowerRightI, this.lowerRightJ);
				
				if(!allRectanglesSeen.containsKey(tempRect)) {
					Rectangle lowerRowLeft=new Rectangle(this.lowerRightI, this.upperLeftJ-1, this.lowerRightI, this.lowerRightJ);
					Rectangle lowerRowRight=new Rectangle(this.lowerRightI, this.upperLeftJ, this.lowerRightI, this.lowerRightJ+1);
					
					if(allRectanglesSeen.containsKey(lowerRowLeft)) {
						profitTemp=allRectanglesSeen.get(lowerRowLeft).profit-parcels.get(transformIndex(this.lowerRightI, this.upperLeftJ-1));
						if(parcels.get(transformIndex(this.lowerRightI, this.upperLeftJ-1))>0){
							potentialTemp=allRectanglesSeen.get(lowerRowLeft).potential-parcels.get(transformIndex(this.lowerRightI, this.upperLeftJ-1));
							
						}else {
							potentialTemp=allRectanglesSeen.get(lowerRowLeft).potential;
						}
					} else if(allRectanglesSeen.containsKey(lowerRowRight)) {
						profitTemp=allRectanglesSeen.get(lowerRowRight).profit-parcels.get(transformIndex(this.lowerRightI, this.lowerRightJ+1));
						if(parcels.get(transformIndex(this.lowerRightI, this.lowerRightJ+1))>0){
							potentialTemp=allRectanglesSeen.get(lowerRowRight).potential-parcels.get(transformIndex(this.lowerRightI, this.lowerRightJ+1));
							
						}else {
							potentialTemp=allRectanglesSeen.get(lowerRowRight).potential;
						}
					} else {
						profitTemp=calculateProfit(this.upperLeftI, this.upperLeftJ, this.upperLeftI, this.lowerRightJ);
						potentialTemp=calculatePotential(this.upperLeftI, this.upperLeftJ, this.upperLeftI, this.lowerRightJ);
					
						
					}
				
					tempRect.setProfit(profitTemp);
					tempRect.setPotential(potentialTemp);
					allRectanglesSeen.put(tempRect, tempRect);
					if(profitTemp>0) {
						retval.add(tempRect);
					}
					Rectangle tempRectInverse=new Rectangle(this.upperLeftI, this.upperLeftJ, this.lowerRightI-1, this.lowerRightJ, this.profit-profitTemp, this.potential-potentialTemp);
					allRectanglesSeen.put(tempRectInverse, tempRectInverse);
					retval.add(tempRectInverse);
					
				}
				
				
			}
			
			return retval;
		}
		
		@Override
		public int compareTo(Rectangle r) {
			return this.profit-r.profit;
		}
		@Override
		public boolean equals(Object obj) {
			Rectangle r= (Rectangle) obj;
			if(this.lowerRightI==r.lowerRightI && this.lowerRightJ==r.lowerRightJ && this.upperLeftI==r.upperLeftI && this.upperLeftJ==r.upperLeftJ) {
				return true;
			}
			return false;
		}

		@Override
		public int hashCode() {
			return this.lowerRightI+this.lowerRightJ+this.upperLeftI+this.upperLeftJ;
		}
		
		
	}
	

}
