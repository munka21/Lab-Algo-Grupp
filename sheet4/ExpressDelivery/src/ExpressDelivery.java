import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.TreeSet;

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
		

		String output="";
		for(int q=0; q<numberOfDeliveries; q++) {
			String[] line= bi.readLine().split(" ");
			droneNetwork.dikstra(Integer.parseInt(line[0])-1,Integer.parseInt(line[1])-1); //starting at 1 instead of 0
			BigDecimal temp=droneNetwork.getNodes().get(Integer.parseInt(line[1])-1).dikstraDistance;
			if(temp!=null) {
				output+=df.format(temp)+" ";
			}
			
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
		
		
		








		





		public void addNode(int nodeNumber, int kmh, int maxDistance) {
			nodes.add(new Node(nodeNumber, kmh, maxDistance, this.numberOfNodes));
		}
		public void addEdge(int startNode, int endNode, int distance) {
			Node startNode2= this.getNodes().get(startNode);
			if(startNode2.maxDistance>=distance) {
				Node endNode2= this.getNodes().get(endNode);
				BigDecimal time=  BigDecimal.valueOf(distance).divide(BigDecimal.valueOf(startNode2.getKmh()), 1000, RoundingMode.HALF_UP);
						
				Edge temp = new Edge(startNode2, endNode2, distance,time );
				
				startNode2.addEdge(temp);

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
					n.setDikstraDistance(BigDecimal.ZERO);
					queue.add(n);
				}
			}
			while(!queue.isEmpty()) {
				Node u=queue.poll();
				//if needed I could add that all shortest paths are stored away here
				if(u.getStationNumber()==endnumber) {
					break;
				}else if(u.getDikstraDistance()!=null){
					for(Edge e: u.getOutgoingEdges()) {
						Node v= e.getEndNode();
						if(queue.contains(v)) {
							//update distance if nessesary:
							BigDecimal temp= u.getDikstraDistance().add(e.timeNeeded);
							if(v.getDikstraDistance()==null ||temp.compareTo(v.getDikstraDistance())==-1) {
								queue.remove(v);
								v.setDikstraDistance(temp);
								
								queue.add(v); //sadly no resorting exists
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
		private BigDecimal dikstraDistance;
		
		
		Node(int nodeNumber, int kmh, int maxDistance, int numberOfNodes){
			this.stationNumber=nodeNumber;
			this.outgoingEdges=new ArrayList<Edge>(numberOfNodes);
			this.kmh=kmh;
			this.maxDistance=maxDistance;
		}

		public ArrayList<Edge> getOutgoingEdges() {
			return this.outgoingEdges;
		}

		public void setDikstraDistance(BigDecimal i) {
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
			this.dikstraDistance=null;
		}

		@Override
		public int compareTo(Node arg0) {
			if(arg0.getDikstraDistance()==null) {
				if(this.getDikstraDistance()==null) {
					return 0;
				}
				else {
					return -1;
				}
			}
			else {
				if( this.getDikstraDistance()==null){
					return 1;
				}
				else {
					return this.getDikstraDistance().subtract(arg0.getDikstraDistance()).signum();
				}
			}
		}

		private BigDecimal getDikstraDistance() {
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
			 private BigDecimal timeNeeded;
		 public Edge(Node startNode, Node endNode, int distance, BigDecimal time) {
			this.startingNode=startNode;
			this.endNode=endNode;
			this.distance=distance;
			this.timeNeeded=time;
		}
		public Node getEndNode() {
			return this.endNode;
		}
		
	 }

}
