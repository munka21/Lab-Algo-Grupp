
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class MesseDeutz {

	public static void main(String[] args)  {
		
		MesseDeutz me = new MesseDeutz();
		try {
			me.doMyThing();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	

	 private void doMyThing() throws IOException {
		// read in 
			BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
			String[] firstLine= bi.readLine().split(" ");
			int numberOfVerticies=Integer.parseInt(firstLine[0]);
			int numberOfEdges=Integer.parseInt(firstLine[1]);
			Graph trainNetwork=new Graph(numberOfVerticies);
			
			for(int i=0; i<numberOfEdges;i++) {
				String[] line= bi.readLine().split(" ");
				trainNetwork.addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
			}
			
			
			
			for(int k: trainNetwork.nodes.keySet()) { //this loop guarantees that all components of the graph are traversed
				Node v=trainNetwork.getNodes().get(k);
				if(v.timeOfDiscovery==null) {
					v.setTimeOfDiscovery(trainNetwork.getTimestep());
					v.setReachableAnchestor(v);
					trainNetwork.tarjan(v);
					trainNetwork.timestep++;
				}
			}
			
			
		
		System.out.println(trainNetwork.critical);
	}
	 
	 





	public class Graph{
		private HashMap<Integer,Node> nodes;
		private int critical;
		private int timestep;
		Graph(int numberNodes){
			nodes= new HashMap<Integer,Node>();
			for(int i=0; i<numberNodes;i++) {
				this.addNode(i, numberNodes);
			}
			critical=0;
			timestep=0;
		}
		
		
		public void tarjan(Node v) {
			for(int k:v.getAdjacentNodes()) {
				
				Node u=this.nodes.get(k);
				if(u.timeOfDiscovery==null) {
					this.timestep++;
					u.setParent(v);
					u.setTimeOfDiscovery(timestep);
					u.setReachableAnchestor(u);
					tarjan(u);
					if(u.reachableAnchestor.timeOfDiscovery<v.reachableAnchestor.timeOfDiscovery) {
						v.setReachableAnchestor(u.getReachableAnchestor());
					}
					if(u.getReachableAnchestor().getTimeOfDiscovery()>=u.getTimeOfDiscovery()) {
						this.critical++;
					}
					
				}else if(!u.equals(v.parent)) {
					if(u.reachableAnchestor.timeOfDiscovery<v.reachableAnchestor.timeOfDiscovery) {
						v.setReachableAnchestor(u.getReachableAnchestor());
					}
				}
				
				
			}
			
		}


		public void addNode(int nodeNumber, int numberOfNodes) {
			nodes.put(nodeNumber,new Node(nodeNumber, numberOfNodes));
		}
		public void addEdge(int startNode, int endNode) {
			nodes.get(startNode).addAdjacentNode(endNode);
			nodes.get(endNode).addAdjacentNode(startNode);
		}
		
		public HashMap<Integer, Node> getNodes(){
			return nodes;
		}


		public int getCritical() {
			return critical;
		}


		public void setCritical(int critical) {
			this.critical = critical;
		}


		public int getTimestep() {
			return timestep;
		}
		
		
	}
	
	 public class Node{
		private int nodeNumber;
		private ArrayList<Integer> adjacentNodes;
		private Node parent;
		private Node reachableAnchestor;
		private Integer timeOfDiscovery;
		
		Node(int nodeNumber, int numberOfNodes){
			this.nodeNumber=nodeNumber;
			this.adjacentNodes=new ArrayList<Integer>(numberOfNodes);
			parent=null;
			reachableAnchestor=null;
			
		}
		public Node getReachableAnchestor() {
			return reachableAnchestor;
		}

		public void setReachableAnchestor(Node reachableAnchestor) {
			this.reachableAnchestor = reachableAnchestor;
		}

		public Integer getTimeOfDiscovery() {
			return timeOfDiscovery;
		}

		public void setTimeOfDiscovery(Integer timeOfDiscovery) {
			this.timeOfDiscovery = timeOfDiscovery;
		}

		
		
		public void addAdjacentNode(int adjacentNodeNumber) {
			adjacentNodes.add( adjacentNodeNumber);
		}
		
		public void setParent(Node parent) {
			this.parent=parent;
		}
		
		public Node getParent() {
			return parent;
		}
		
		public int getNodeNumber() {
			return this.nodeNumber;
		}
		
		public ArrayList<Integer> getAdjacentNodes(){
			return this.adjacentNodes;
		}
	}
	 
	

}

