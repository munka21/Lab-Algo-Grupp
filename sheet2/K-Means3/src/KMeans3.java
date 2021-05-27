import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;


public class KMeans3 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
		String[] firstLine= bi.readLine().split(" ");
		int dimension=Integer.parseInt(firstLine[0]);
		BigInteger r=BigInteger.valueOf(Integer.parseInt(firstLine[1].replace(".","")));
		// to avoid taking squareroots: 
		r= r.pow(2);
		String line;
		
		TreeSet<Point> centers=new TreeSet<Point>();
		int index=0;
		while((line=bi.readLine())!=null) {
			
			String[] temp = line.replace(".","").split(" ");
			ArrayList<Long> values= new ArrayList<Long>(dimension);
			for(int i=0; i<dimension;i++) {
				values.add(Long.parseLong(temp[i]));
			}
			Point p=new Point(values, index);
			index++;
			
			if(!p.findNearestPoint(centers,r)) {
				centers.add( p);
			}
		}
		
		System.out.println(centers.size());
	}

}

class Point implements Comparable<Point>{

	private ArrayList<Long> values;
	private int index;
	private BigInteger distToZero;
	private int dimension;

	
	
	public Point(ArrayList<Long> values, int index) {
		this.values=values;
		this.index=index;
		this.distToZero=BigInteger.ZERO;
		for(long v: values) {
			
			distToZero=distToZero.add(BigInteger.valueOf(v).pow(2));
		}
		this.dimension=this.values.size();
	}
	public boolean findNearestPoint(TreeSet<Point> centers, BigInteger r) {
		//check if p itself is already in the list of centers:
		if(centers.size()==0) {
			return false;
		}
		else {

			BigInteger binomi_temp= r.multiply(this.distToZero).multiply(BigInteger.valueOf(2));//definetly greater than 2*distance*r
			
			BigInteger minDistance= this.distToZero.subtract(binomi_temp);
			BigInteger maxDistance=this.distToZero.add(r).add(binomi_temp);
			Point fromPoint=new Point(new ArrayList<Long>(), this.index);
			Point toPoint=new Point(new ArrayList<Long>(), 0);
			fromPoint.setDistToZero(minDistance);
			toPoint.setDistToZero(maxDistance);
			SortedSet<Point> toSearch=centers.subSet(fromPoint, toPoint);
			for(Point c: toSearch) {
				if(this.calculateDistance(c, r).compareTo(r)<=0) {
					return true;
				};
			}
			
			
			return false;
			
	
		}
		
		
		
		
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	public ArrayList<Long> getValues() {
		return values;
	}
	public BigInteger getDistToZero() {
		return distToZero;
	}
	public void setDistToZero(BigInteger distToZero) {
		this.distToZero=distToZero;
	}
	
	//maybe need to switch to BigInteger and maybe need to use R here to avoid calculation much more.
	public BigInteger calculateDistance(Point p, BigInteger maxDistance) {
		BigInteger distance=BigInteger.ZERO; //If we would not need to spare time here we need to check dimensions of both points first
		for(int i=0; i<this.dimension; i++) {
			BigInteger temp=BigInteger.valueOf(this.getValues().get(i)).subtract(BigInteger.valueOf(p.getValues().get(i)));
			distance= distance.add(temp.pow(2));
			if(distance.compareTo(maxDistance)==1) {
				return maxDistance.add(BigInteger.ONE);
			}
		}
		return distance;
		
	}
	@Override
	public int compareTo(Point p) {
		if(this.distToZero==p.getDistToZero()) {
			return p.index -this.index; //sorts index from big to small
		}
		
		return this.distToZero.subtract(p.getDistToZero()).signum()  ;
	}
	
	
	
	
}
