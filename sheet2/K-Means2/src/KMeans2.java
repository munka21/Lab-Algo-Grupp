
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.TreeSet;

public class KMeans2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
		String[] firstLine= bi.readLine().split(" ");
		int numberOfCenters=Integer.parseInt(firstLine[0]);
		int numberOfQueryPoints=Integer.parseInt(firstLine[1]);
		int r=Integer.parseInt(firstLine[2].replace(".",""));
		TreeSet<Point> points=new TreeSet<>();
		ArrayList<Q> queryPoints=new ArrayList<Q>(numberOfQueryPoints);
		
		for (int i=1; i<=numberOfCenters;i++) {
			String temp=bi.readLine().replace(".","");
			points.add(new Point(Integer.parseInt(temp), -i));
		}
		for(int i=0; i< numberOfQueryPoints;i++) {
			String temp=bi.readLine().replace(".","");
			Q q=new Q(Integer.parseInt(temp), i);
			points.add(q);
			queryPoints.add(q);
		}
		//sort List:
		//Collections.sort(points);
		Integer lastC=null;
		for(Point p : points) {
			if(Q.class.isInstance(p)) {
				((Q)p).setSmallerC(lastC);
			} else {
				lastC=p.getValue();
			}
		}
		Integer nextC=null;
		Iterator<Point> iterator =points.descendingIterator();
		while(iterator.hasNext()){
			Point p= iterator.next();
			if(Q.class.isInstance(p)) {
				((Q)p).setGreaterC(nextC);
			} else {
				nextC=p.getValue();
			}
		}
		
		//Collections.sort(points, new QCComparator());
		
		for(Q q :queryPoints) {
			
			System.out.println(q.getNearestCOutput(r));
			
		}
		
		
		/*
		 ArrayList<Integer> centers =new ArrayList<>();
		 for (int i=0; i<numberOfCenters;i++) {
			String temp=bi.readLine().replace(".","");
			centers.add(Integer.parseInt(temp));
		}
		Collections.sort(centers);
		 
		for (int i=0; i<numberOfQueryPoints;i++) {
			String temp= bi.readLine().replace(".", "");
			int q= Integer.parseInt(temp);
			Integer nearestCenter=null;
			if(centers.size()>1) {
				Collections.binarySearch(centers, q);
				int leftIndex= Collections.binarySearch(centers, q);
				if(leftIndex<0 ) {
					leftIndex = (-leftIndex - 2);
				}
				if(leftIndex>=0 & leftIndex!=centers.size()-1) {
					int leftCenter= centers.get(leftIndex);
					int rightCenter= centers.get(leftIndex+1);
					int rightDistance= Math.abs(rightCenter-q);
					int leftDistance= Math.abs(q-leftCenter);
					if(rightDistance<=r || leftDistance<=r) {
						if(rightDistance>=leftDistance){
							nearestCenter=leftCenter;
						}else {
							
							nearestCenter=rightCenter;
							
						}
					}
				} else if(leftIndex<0 & Math.abs(centers.get(0)-q)<=r) {
					nearestCenter=centers.get(0);
				}else if(leftIndex==centers.size()-1 && Math.abs(centers.get(leftIndex)-q)<=r ) {
					
					nearestCenter=centers.get(leftIndex);
					
					
				}
			} else if(centers.size()==1 && Math.abs(centers.get(0)-q)<=r) {
				
				nearestCenter=centers.get(0);
				
			}
			
			
			if(nearestCenter==null) {
				System.out.println("none in range");
			} else {
				System.out.println(java.lang.String.format("%.02f", (double)nearestCenter/100));
			}
		}*/
		
		
		

	}
	
	

	
}
class Point implements Comparable{

	private int value;

	private int index;
	public Point(int value, int index) {
		this.setValue(value);
		this.index=index;
	}
	@Override
	public int compareTo(Object p) {
		if(this.value==((Point)p).getValue()) {
			return this.index- ((Point)p).index;
		}
		
		return this.value -((Point)p).getValue() ;
		
		
	}
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	
	
}
final class Q extends Point{
	private Integer smallerC;
	private Integer greaterC;
	
	public Q(int value, int index) {
		super(value, index);
	}
	
	public void setSmallerC(Integer c) {
		this.smallerC=c;
	}
	
	public Integer getSmallerC() {
		return this.smallerC;
	}
	public void setGreaterC(Integer c) {
		this.greaterC=c;
	}
	
	public Integer getGreaterC() {
		return this.greaterC;
	}
	
	public int getValue() {
		return super.getValue();
	}

	
	public String getNearestCOutput(int maxDistance) {
		Integer leftDistance=null;
		Integer rightDistance=null;
		if(smallerC!=null) {

			leftDistance=Math.abs(this.getValue()-this.smallerC);
		} 
		if(greaterC!=null) {
			rightDistance=Math.abs(this.greaterC-this.getValue());
		}
		if(leftDistance!=null&& rightDistance!=null) {
			
			if(leftDistance<=maxDistance && leftDistance<=rightDistance) {
				return java.lang.String.format("%.02f", (double)this.smallerC/100);
			}else if(rightDistance<=maxDistance ) {
				return java.lang.String.format("%.02f", (double)this.greaterC/100);
			}
		} else if(leftDistance!=null && leftDistance<=maxDistance) {
			return java.lang.String.format("%.02f", (double)this.smallerC/100);
		} else if(rightDistance!=null && rightDistance<=maxDistance) {
			return java.lang.String.format("%.02f", (double)this.greaterC/100);
		} 
		return "none in range";
		
		
		
	}
}

 class QCComparator implements Comparator<Point>{

	@Override
public int compare(Point arg0, Point arg1) {
		//sorts Q before C
		if(Q.class.isInstance(arg0) && Q.class.isInstance(arg1)) {
			return ((Q)arg0).getIndex()-((Q)arg1).getIndex();
		}else if( Q.class.isInstance(arg0) && !Q.class.isInstance(arg1)) {
			return -1;
		}else if( Q.class.isInstance(arg1) && !Q.class.isInstance(arg0)) {
			return 1;
		} else {
			return arg0.compareTo(arg1);
		}
		
		
	}
	
}
