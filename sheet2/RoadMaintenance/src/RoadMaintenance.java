import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class RoadMaintenance {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
		int numberOfPoints=Integer.parseInt(bi.readLine());
		ArrayList<Integer> xValues=new ArrayList<Integer>(numberOfPoints);
		ArrayList<Integer> yValues=new ArrayList<Integer>(numberOfPoints);
		ArrayList<Line2D> segments=new ArrayList<Line2D>(numberOfPoints);
		

		String[] temp =bi.readLine().split(" ");
		int xLast=Integer.parseInt(temp[0]);
		int yLast=Integer.parseInt(temp[1]);
		xValues.add(xLast);
		yValues.add(yLast);
		for (int i=0; i<numberOfPoints-1; i++) {
			 temp =bi.readLine().split(" ");
			 
			int xNew=Integer.parseInt(temp[0]);
			int yNew=Integer.parseInt(temp[1]);
			segments.add(new Line2D.Double(xLast, yLast, xNew, yNew));
			xValues.add(xNew);
			yValues.add(yNew);
			xLast=xNew;
			yLast=yNew;
			
		}
		segments.add(new Line2D.Double(xLast, yLast, xValues.get(0), yValues.get(0)));
		int xOutside=Collections.max(xValues)+1;
		int yOutside=Collections.max(yValues)+1;
		
		
		//define which points may lie in the polygon:
		int xLeftside=Collections.min(xValues);
		int xRightside=xOutside-1;
		int yBotton=Collections.min(yValues);
		int yTop=yOutside-1;
		
		//bruteforce through the points in this rectangle
		int crossroads=0;
		for(int y=yBotton; y<=yTop; y++) {
			for(int x=xLeftside; x<=xRightside; x++) {
				// check if line between point and outside point intersects with any of the Polygonsegments
				if(checkIfInside( segments, x, y, xOutside, yOutside)) {
					crossroads++;
				}
			}
		}
		
		System.out.print(crossroads);

	}
	
	public static boolean checkIfInside(ArrayList<Line2D> segments, int x, int y, int xOutside, int yOutside) {
		double counter=0;
		for(Line2D s: segments) {
			if(checkIfOnLine(s,x,y)) {
				return true;
			} else  {
				
				//always put in the first point of line in first 
				counter+= checkIfIntersect(x,y, xOutside, yOutside, (int)s.getX1(), (int)s.getY1(), (int)s.getX2(), (int)s.getY2());
			}
		}
		if(counter%2==1) {
			return true;
		}
		return false;
	}
	public static boolean checkIfOnLine(Line2D line, int x,int  y) {
		int dxc = (int) (x - line.getX1());
		int dyc = (int) (y - line.getY1());

		int dxl = (int) (line.getX2() -line.getX1());
		int dyl = (int) (line.getY2() - line.getY1());

		long cross = dxc * dyl - dyc * dxl;
		if(cross==0) {
			return true;
		} else {
			return false;
		}

	}
	
	//something is wrong with right angles
	public static double checkIfIntersect(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
		  double denom = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
		  if (denom == 0.0) { // Lines are parallel.
		     return 0;
		  }
		  int ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3));
		  int ub = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3));
		    if (ua >= 0 && ua <= denom && ub >= 0 && ub <= denom) {
		        // Get the intersection point.
		    	if( ub==0 ) {
		    		//if intersection point is equal to endpoint x3, y3 //TODO think this through
		    		return 0;
		    	}
		    	else {
		    		return 1;
		    	}
		    }

		  return 0;
		  }

}
