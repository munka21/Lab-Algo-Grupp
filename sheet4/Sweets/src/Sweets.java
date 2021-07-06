import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;



public class Sweets {

	public static void main(String[] args) {
		Sweets me=new Sweets();
		try {
			me.doMyThing();
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void doMyThing() throws NumberFormatException, IOException {
		// read in
		BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
		int numberOfChildren = Integer.parseInt(bi.readLine());
		int numberOfSweetTypes = Integer.parseInt(bi.readLine());
		ArrayList<Integer> availableSweets= new ArrayList<Integer>();
		String[] thirdLine= bi.readLine().split(" ");
		int sumSweets=0;
		for(String s: thirdLine) {
			sumSweets+=Integer.parseInt(s);
			availableSweets.add(Integer.parseInt(s));
		}
		Graph matchingGraph = new Graph(numberOfChildren, availableSweets, sumSweets);
		
		//create edges between children and sweets with capacity numberSweetsPerChild and cost the preference
		int numberSweetsPerChild= sumSweets/numberOfChildren;
		for (int i = 0; i < numberOfChildren; i++) {
			int missingSweets=numberSweetsPerChild;
			String[] line = bi.readLine().split(" ");
			Node startNode = matchingGraph.nodes.get("c"+i);
			for(int j=0; j<line.length;j++) {
				Node endNode= matchingGraph.nodes.get("s"+j);
				int cost=Integer.parseInt(line[j]);
				
				Edge temp=new Edge(startNode, endNode, numberSweetsPerChild, cost);
				matchingGraph.edges.add(temp);
				startNode.addOutgoingEdge(temp);
				endNode.addIncomingEdge(temp);
				if(missingSweets >0) { //creates a feasable b flow
					Edge e=endNode.outgoingEdges.get("t");
					if(!e.isSaturated()) {
						
						int possibleSweets= Math.min(e.capacity-e.flow, missingSweets);
						
						temp.addFlow(possibleSweets);
						missingSweets=missingSweets-possibleSweets;
						e.endNode.demand-=possibleSweets;
						e.addFlow(possibleSweets);
					}
				}
				
			}
			
		}
		//calculate best matching
		ArrayList<Node> negativeCycle=matchingGraph.isNegCycleBellmannFord();
		while(negativeCycle.size()>0) {
			matchingGraph.updateFlow(negativeCycle);
			negativeCycle=matchingGraph.isNegCycleBellmannFord();
		}
		
		//find maximmum priority used:
		int maxPrio=0;
		for(Edge e: matchingGraph.edges) {
			if(e.flow>0) {
				if(e.cost>maxPrio) {
					maxPrio=e.cost;
				}
			}
		}
		
		System.out.println(maxPrio);
		
		
		
		
		
		
	}
	
	
	public class Graph{
		private HashMap<String,Node> nodes;
		private ArrayList<Edge> edges;
		Graph(int numberChildren, ArrayList<Integer> sweets, int sumSweets){
			nodes= new HashMap<String,Node>();
			edges= new ArrayList<Edge>();
			int numberSweetsPerChild= sumSweets/numberChildren;
			
			Node sink= new Node("t");
			
			//Node source= new Node("s");
			
			//add edge between t and s with 
			//Edge sink_source= new Edge(sink, source, sumSweets, -1);
			//sink_source.addFlow(5);
			//source.addIncomingEdge(sink_source);
			//sink.addOutgoingEdge(sink_source);
			//edges.add(sink_source);
			
			//nodes.put(source.nodeName, source);
			nodes.put(sink.nodeName, sink);
			sink.demand=numberSweetsPerChild*numberChildren;
			//create nodes for all children 
			for(int i=0; i<numberChildren; i++) {
				Node childNode=new Node("c"+i);
				nodes.put(childNode.nodeName,childNode);
				childNode.demand=-numberSweetsPerChild;
				//Edge temp=new Edge(source, childNode, numberSweetsPerChild,0);
				//edges.add(temp);
				//temp.setFlow(numberSweetsPerChild);
				//source.addOutgoingEdge(temp);
				//childNode.addIncomingEdge(temp);
				
			}
			
			//create nodes for all sweets and connect them with the sink
			for(int i=0; i< sweets.size();i++) {
					Node sweetNode=new Node("s"+i);
					nodes.put(sweetNode.nodeName, sweetNode);
					
					Edge temp=new Edge(sweetNode, sink, sweets.get(i), 0);
					edges.add(temp);
					sweetNode.addOutgoingEdge(temp);
					sink.addIncomingEdge(temp);
					
				
			}
			
			
			
			
			
			
			
		}
		
		public void updateFlow(ArrayList<Node> negativeCycle) {
			int minimumAugmentableValue=Integer.MAX_VALUE;
			
			for(int i=negativeCycle.size()-1; i>0;i--) {
				Node actualNode=negativeCycle.get(i);
				Node nextNode= negativeCycle.get(i-1);
				Edge e;
				if(actualNode.outgoingEdges.containsKey(nextNode.nodeName)){
					e= actualNode.outgoingEdges.get(nextNode.nodeName);
					if(e.capacity-e.flow < minimumAugmentableValue) {
						minimumAugmentableValue=e.capacity-e.flow;
					}
				} else {
					e=actualNode.incomingEdges.get(nextNode.nodeName);
					if(e.flow < minimumAugmentableValue) {
						minimumAugmentableValue=e.flow;
					}
				}
				
			}
			
			for(int i=negativeCycle.size()-1; i>0;i--) {
				Node actualNode=negativeCycle.get(i);
				Node nextNode= negativeCycle.get(i-1);
				Edge e;
				if(actualNode.outgoingEdges.containsKey(nextNode.nodeName)){
					e= actualNode.outgoingEdges.get(nextNode.nodeName);
					e.addFlow(minimumAugmentableValue);
				} else {
					e=actualNode.incomingEdges.get(nextNode.nodeName);
					e.addFlow(-minimumAugmentableValue);
				}
				
			}
			
		}

		public ArrayList<Node> isNegCycleBellmannFord() {
			for(String key: this.nodes.keySet()) {
				if(key.endsWith("c0")) {
					this.nodes.get(key).setBellmannDist(0);
				}
				else {
					this.nodes.get(key).resetBellmannDist();
				}
			}
			for(int i=0; i< this.nodes.size();i++) {
				for(Edge e: this.edges) {
					//normal edge if not saturated:
					if(!e.isSaturated()) {
						if(e.startNode.bellmanDist!= Integer.MAX_VALUE && e.startNode.bellmanDist+e.cost< e.endNode.bellmanDist) {
							e.endNode.setBellmannDist(e.startNode.bellmanDist+e.cost);
							e.endNode.bellmannPredecessor=e.startNode;
						}
					}
					
					//if flow is on that edge backward edge:
					if(e.getFlow()>0) {
						if(e.endNode.bellmanDist!=Integer.MAX_VALUE && e.endNode.bellmanDist-e.cost<e.startNode.bellmanDist) {
							e.startNode.setBellmannDist(e.endNode.bellmanDist-e.cost);
							e.startNode.bellmannPredecessor=e.endNode;
						}
					}
				}
			}
			
			//check for cycles
			ArrayList<Node> negativeCycle=new ArrayList<Node>();
			for(Edge e: this.edges) {
				//normal edge if not saturated:
				if(!e.isSaturated()) {
					if(e.startNode.bellmanDist!= Integer.MAX_VALUE && e.startNode.bellmanDist+e.cost< e.endNode.bellmanDist) {
						negativeCycle.add(e.endNode);
						negativeCycle.add(e.startNode);
						break;
					}
				}
				
				//if flow is on that edge backward edge:
				if(e.getFlow()>0) {
					if(e.endNode.bellmanDist!=Integer.MAX_VALUE && e.endNode.bellmanDist-e.cost<e.startNode.bellmanDist) {
						negativeCycle.add(e.startNode);
						negativeCycle.add(e.endNode);
						break;
					}
				}
			}
			
			//get all the nodes allong this cycle:
			if(negativeCycle.size()>0) {
				Node cycleNode= negativeCycle.get(1);
				while(!negativeCycle.contains(cycleNode.bellmannPredecessor)) {
					cycleNode=cycleNode.bellmannPredecessor;
					negativeCycle.add(cycleNode);
				}
				Node CycleStart= cycleNode.bellmannPredecessor;
				int index= negativeCycle.indexOf(CycleStart);
				for(int i=0; i<index;i++) {
					negativeCycle.remove(0);
				}
				negativeCycle.add(CycleStart);
			}
			
			return negativeCycle;
			
			
		}
		
		public void addEdge(Node startNode, Node endNode, int capacity, int cost) {
			Edge temp= new Edge(startNode, endNode, capacity, cost);
			startNode.addOutgoingEdge(temp);
			endNode.addIncomingEdge(temp);
		}
	}
	
	public class Node{
		private String nodeName;
		private HashMap<String,Edge> outgoingEdges;
		private HashMap<String, Edge> incomingEdges;
		private int bellmanDist;
		private Node bellmannPredecessor;
		private int demand;
		
		public Node(String nodeName) {
			this.nodeName=nodeName;
			this.outgoingEdges=new HashMap<String, Edge>();
			this.incomingEdges=new HashMap<String, Edge>();
		}

		public void addIncomingEdge(Edge edge) {
			incomingEdges.put(edge.startNode.nodeName, edge);
			
		}
		
		public void resetBellmannDist() {
			this.bellmanDist=Integer.MAX_VALUE;
		}
		
		public void setBellmannDist(int dist) {
			this.bellmanDist=dist;
		}

		public void addOutgoingEdge(Edge edge) {
			outgoingEdges.put(edge.endNode.nodeName, edge);
			
		}
	}
	
	public class Edge{
		private int capacity;
		private int cost;
		private Node startNode;
		private Node endNode;
		private int flow;
		
		public Edge(Node startNode, Node endNode, int capacity, int cost) {
			this.startNode=startNode;
			this.endNode=endNode;
			this.capacity=capacity;
			this.cost=cost;
		}
		
		public void addFlow(int additionalFlow) {
			this.flow+=additionalFlow;
			
		}

		public int getFlow() {
			return this.flow;
		}

		public int getCapacity() {
			return this.capacity;
		}

		public void setFlow(int flow) {
			this.flow=flow;
		}
		
		public boolean isSaturated() {
			if(flow<capacity) {
				return false;
			}
			else {
				return true;
			}
		}
	}

}
