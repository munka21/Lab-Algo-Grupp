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
		long r=Long.parseLong(firstLine[1].replace(".",""));
		// to avoid taking squareroots: 
		long r_squared= r*r;
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
			
			if(!p.findNearestPoint(centers,r, r_squared)) {
				centers.add( p);
			}
		}
		
		System.out.println(centers.size());
	}

}

class Point implements Comparable<Point>{

	private ArrayList<Long> values;
	private int index;
	private long norm;
	private double distanceToZero;
	private int dimension;

	
	
	public Point(ArrayList<Long> values, int index) {
		this.values=values;
		this.index=index;
		this.norm=0;
		for(long v: values) {
			
			norm+=v*v;
		}
		this.dimension=this.values.size();
		this.distanceToZero=Math.sqrt(norm);
	}
	public boolean findNearestPoint(TreeSet<Point> centers, long r, long r_squared) {
		//check if p itself is already in the list of centers:
		if(centers.size()==0) {
			return false;
		}
		else {
			double minDistance=Math.floor((this.distanceToZero-r));
			double maxdistance= Math.ceil(this.distanceToZero+r);
			
			Point fromPoint=new Point(new ArrayList<Long>(), this.index);
			Point toPoint=new Point(new ArrayList<Long>(), 0);
			fromPoint.setDistanceToZero(minDistance);
			toPoint.setDistanceToZero(maxdistance);
			SortedSet<Point> toSearch=centers.subSet(fromPoint, true, toPoint, true);
			for(Point c: toSearch) {
				if(this.calculateDistance(c, r_squared)<=r_squared) {
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
	public long getNorm() {
		return norm;
	}
	public void setNorm(long norm) {
		this.norm=norm;
	}
	
	public double getDistanceToZero() {
		return distanceToZero;
	}
	public void setDistanceToZero(double distanceToZero) {
		this.distanceToZero=distanceToZero;
	}
	
	
	//maybe need to switch to BigInteger and maybe need to use R here to avoid calculation much more.
	public long calculateDistance(Point p, long maxDistance) {
		long distance=0; //If we would not need to spare time here we need to check dimensions of both points first
		for(int i=0; i<this.dimension; i++) {
			long temp=this.getValues().get(i)-p.getValues().get(i);
			distance= distance+temp*temp;
			if(distance >maxDistance) {
				return distance;
			}
		}
		return distance;
		
	}
	@Override
	public int compareTo(Point p) {
		if(this.distanceToZero==p.getDistanceToZero()) {
			return p.index -this.index; //sorts index from big to small
		}
		
		return (int) Math.signum((this.distanceToZero-(p.getDistanceToZero())));
	}
	
	
	
	
}
