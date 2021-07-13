import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class ExpressDelivery {

	private static DecimalFormat df = new DecimalFormat("0.00");
	public static void main(String[] args) {
		ExpressDelivery me=new ExpressDelivery();
		try {
			me.doMyThing();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void doMyThing() throws IOException {
		//read in
		BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
		String[] firstLine= bi.readLine().split(" ");
		int numberOfStations=Integer.parseInt(firstLine[0]);
		int numberOfDeliveries=Integer.parseInt(firstLine[1]);
		Graph droneNetwork=new Graph(numberOfStations);
		for(int i=0; i<numberOfStations;i++) {
			String[] line= bi.readLine().split(" ");
			droneNetwork.addNode(i, Integer.parseInt(line[1]),Integer.parseInt(line[0]));
		}
		for(int i=0; i<numberOfStations;i++) {

			String[] line= bi.readLine().split(" ");
			for(int j=0; j<numberOfStations;j++) {
				if(Integer.parseInt(line[j])!=-1) {
					
					droneNetwork.addEdge(i, j, Integer.parseInt(line[j]));
				}
				
			}
		}
		
		droneNetwork.addOptimalEdges();
		double[][] minDist=droneNetwork.floydWarschall();
		String output="";
		for(int q=0; q<numberOfDeliveries; q++) {
			String[] line= bi.readLine().split(" ");
			output+=df.format(Math.round(minDist[Integer.parseInt(line[0])-1][Integer.parseInt(line[1])-1]*100d)/100d)+" ";
				}
		//remove empty space at the end:
		output = output.substring(0, output.length() - 1);
		System.out.println(output);
		
	}
	
	
	public class Graph{
		private ArrayList<Node> nodes;
		private int numberOfNodes;
		Graph(int numberNodes){
			nodes= new ArrayList<Node>(numberNodes);
			this.numberOfNodes=numberNodes;
			
		}
		
		
		


		public void addOptimalEdges() {
			ArrayList<Edge> additionalEdges = new ArrayList<Edge>();
			boolean again=false;
			for(Node u : this.nodes) {
				for(int j: u.getOutgoingEdges().keySet()) {
					Edge e=u.getOutgoingEdges().get(j);
					if(e.getFlyFurther()) {
						int distanceAvailable= u.maxDistance-e.distance;
						Node v= e.endNode;
						for(int i: v.getOutgoingEdges().keySet()) {
							Edge e2=v.getOutgoingEdges().get(i);
							if(e2.distance<=distanceAvailable && !(u.stationNumber==e2.endNode.stationNumber)) {
								Node startNode=u;
								Node endNode=e2.endNode;
								int distance=e.distance+e2.distance;
								double time= (double)distance/startNode.getKmh();
								
								if(!checkForBetterEdge(startNode, endNode, time)) {
									Edge temp = new Edge(startNode, endNode, distance,time );
									if(startNode.maxDistance>distance ) {
										temp.setFlyFurtherTrue();
									}
									additionalEdges.add(temp);
								}
								
								
							}
							
						}
						e.setFlyFurtherFalse();
					}
				}
			}
			
			for(Edge e: additionalEdges) {
				//need to check if we haven't already added a better edge in this loop.
				if(!checkForBetterEdge(e.startingNode, e.endNode, e.timeNeeded)) {
					e.startingNode.addEdge(e);
					if(e.flyFurther) {
						again=true;
						
					}
				}
				
			}
			if(again) {
				addOptimalEdges();
			}
			
		}





		





		private boolean checkForBetterEdge(Node startNode, Node endNode, double time) {
			
			if(startNode.outgoingEdges.containsKey(endNode.stationNumber)) {
				Edge e=startNode.outgoingEdges.get(endNode.stationNumber);
				if(e.usable && e.timeNeeded<= time) {
					return true;
				}
			}
			
			
			
			return false;
		}





		public void addNode(int nodeNumber, int kmh, int maxDistance) {
			nodes.add(new Node(nodeNumber, kmh, maxDistance, this.numberOfNodes));
		}
		public void addEdge(int startNode, int endNode, int distance) {
			Node startNode2= this.getNodes().get(startNode);
			
			Node endNode2= this.getNodes().get(endNode);
			double time= (double)distance/startNode2.getKmh();
			Edge temp = new Edge(startNode2, endNode2, distance,time );
			if(startNode2.maxDistance<distance) {
				temp.setUsableFalse();
			}
			startNode2.addEdge(temp);
				
			if(startNode2.maxDistance>distance) {
				temp.setFlyFurtherTrue();
			} 
			
			
		}
		
		public ArrayList<Node> getNodes(){
			return nodes;
		}

		public double[][] floydWarschall() {
			double[][] minimalDistance = new double[this.numberOfNodes][this.numberOfNodes];
			for (Node v : this.nodes) {
				int i = v.stationNumber;
				for (Node w : this.nodes) {
					int j = w.stationNumber;
					minimalDistance[i][j] = Integer.MAX_VALUE;
				}
			}

			for (Node n : this.nodes) {
				minimalDistance[n.stationNumber][n.stationNumber] = 0;
				for (int i : n.outgoingEdges.keySet()) {
					Edge e=n.outgoingEdges.get(i);
					if (e.usable) {
						if (minimalDistance[n.stationNumber][e.endNode.stationNumber] > e.timeNeeded) {

							minimalDistance[n.stationNumber][e.endNode.stationNumber] = e.timeNeeded;
						}
					}
				}
			}

			for (Node u : this.nodes) {
				int k = u.stationNumber;
				for (Node v : this.nodes) {
					int i = v.stationNumber;
					for (Node w : this.nodes) {
						int j = w.stationNumber;
						if (minimalDistance[i][j] > minimalDistance[i][k] + minimalDistance[k][j]) {
							minimalDistance[i][j] = minimalDistance[i][k] + minimalDistance[k][j];
						}
					}
				}
			}
			return minimalDistance;
		}
		

		
		
	}
	
	 public class Node {
		private int stationNumber;
		private HashMap<Integer,Edge> outgoingEdges;
		private int kmh;
		private int maxDistance;
		
		
		
		Node(int nodeNumber, int kmh, int maxDistance, int numberOfNodes){
			this.stationNumber=nodeNumber;
			this.outgoingEdges=new HashMap<Integer,Edge>();
			this.kmh=kmh;
			this.maxDistance=maxDistance;
		}

		public HashMap<Integer,Edge> getOutgoingEdges() {
			return this.outgoingEdges;
		}


		public int getStationNumber() {
			return this.stationNumber;
		}

		public void addEdge(Edge temp) {
			outgoingEdges.put(temp.endNode.stationNumber, temp);
			
		}

		public int getKmh() {
			return kmh;
		}
		
		
	

		

		@Override
		public boolean equals(Object obj) {
			Node otherNode=(Node) obj;
			
			return this.stationNumber==otherNode.stationNumber;
		}
		
		
	}
	 
	 public class Edge{

			private Node startingNode;
			 private Node endNode;
			 private int distance;
			 private double timeNeeded;
			 private boolean flyFurther;
			 private boolean usable;
			 
		 public Edge(Node startNode, Node endNode, int distance, double time) {
			this.startingNode=startNode;
			this.endNode=endNode;
			this.distance=distance;
			this.timeNeeded=time;
			this.flyFurther=false;
			this.usable=true;
		}
		public void setFlyFurtherFalse() {
			this.flyFurther=false;
			
		}
		public Node getEndNode() {
			return this.endNode;
		}
		
		public boolean getFlyFurther() {
			return this.flyFurther;
		}
		
		public void setFlyFurtherTrue() {
			this.flyFurther=true;
		}
		
		public void setUsableFalse() {
			this.usable=false;
		}
	 }

}