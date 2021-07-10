import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
		String output="";
		for(int q=0; q<numberOfDeliveries; q++) {
			String[] line= bi.readLine().split(" ");
			droneNetwork.dikstra(Integer.parseInt(line[0])-1,Integer.parseInt(line[1])-1); //starting at 1 instead of 0
			output+=df.format(droneNetwork.getNodes().get(Integer.parseInt(line[1])-1).dikstraDistance)+" ";
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
				for(Edge e: u.getOutgoingEdges()) {
					if(e.getFlyFurther()) {
						int distanceAvailable= u.maxDistance-e.distance;
						Node v= e.endNode;
						for(Edge e2: v.getOutgoingEdges()) {
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
				e.startingNode.addEdge(e);
				if(e.flyFurther) {
					again=true;
					
				}
			}
			if(again) {
				addOptimalEdges();
			}
			
		}





		





		private boolean checkForBetterEdge(Node startNode, Node endNode, double time) {
			for(Edge e: startNode.outgoingEdges) {
				if(e.endNode.stationNumber==endNode.stationNumber) {
					if(e.timeNeeded< time) {
						return true;
					}
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
				startNode2.setNonUsableEdgeTrue();
			}
			startNode2.addEdge(temp);
				
			if(startNode2.maxDistance>distance) {
				temp.setFlyFurtherTrue();
			} 
			
			
		}
		
		public ArrayList<Node> getNodes(){
			return nodes;
		}
		
		public void dikstra(int startnumber, int endnumber) {
			//initialize Queue
			PriorityQueue<Node> queue= new PriorityQueue<Node>();
			for(Node n: nodes) {
				if(n.getStationNumber()!=startnumber) {
					n.resetDikstraDistance();
					queue.add(n);
				}else {
					n.setDikstraDistance(0);
					queue.add(n);
				}
			}
			while(!queue.isEmpty()) {
				Node u=queue.poll();
				//if needed I could add that all shortest paths are stored away here
				if(u.getStationNumber()==endnumber) {
					break;
				}else {
					for(Edge e: u.getOutgoingEdges()) {
						if(e.usable) {
							Node v= e.getEndNode();
							if(queue.contains(v)) {
								//update distance if nessesary:
								if(u.getDikstraDistance()+e.timeNeeded<v.getDikstraDistance()) {
									queue.remove(v);
									v.setDikstraDistance(u.getDikstraDistance()+e.timeNeeded);
									
									queue.add(v); //sadly no resorting exists
								}
							}
						}
						
					}
					
				}
				
			}
		}


		
		
	}
	
	 public class Node implements Comparable<Node>{
		private int stationNumber;
		private ArrayList<Edge> outgoingEdges;
		private int kmh;
		private int maxDistance;
		private double dikstraDistance;
		private boolean nonUsableEdge;
		
		
		Node(int nodeNumber, int kmh, int maxDistance, int numberOfNodes){
			this.stationNumber=nodeNumber;
			this.outgoingEdges=new ArrayList<Edge>(numberOfNodes);
			this.kmh=kmh;
			this.maxDistance=maxDistance;
			this.nonUsableEdge=false;
		}

		public ArrayList<Edge> getOutgoingEdges() {
			return this.outgoingEdges;
		}

		public void setDikstraDistance(double i) {
			this.dikstraDistance=i;
			
		}

		public int getStationNumber() {
			return this.stationNumber;
		}

		public void addEdge(Edge temp) {
			outgoingEdges.add(temp);
			
		}

		public int getKmh() {
			return kmh;
		}
		
		public void resetDikstraDistance() {
			this.dikstraDistance=Integer.MAX_VALUE;
		}
		
		public void setNonUsableEdgeTrue() {
			this.nonUsableEdge=true;
		}

		@Override
		public int compareTo(Node arg0) {
			return  (int) Math.signum(this.dikstraDistance-arg0.getDikstraDistance());
		}

		private double getDikstraDistance() {
			return this.dikstraDistance;
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
