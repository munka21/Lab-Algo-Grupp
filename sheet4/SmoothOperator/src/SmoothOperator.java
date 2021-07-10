import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


public class SmoothOperator {

	public static void main(String[] args) {
		SmoothOperator me= new SmoothOperator();
		try {
			me.doMyThing();
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void doMyThing() throws NumberFormatException, IOException{
		//read in
		BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
		int numberOfNodes=Integer.parseInt(bi.readLine());
		int numberOfLinks=Integer.parseInt(bi.readLine());
		Graph network=new Graph(numberOfNodes);
		for(int i=0; i<numberOfLinks;i++) {
			String[] line= bi.readLine().split(" ");
			network.addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Double.parseDouble(line[2]));
		}
		
		if(checkSubsets(network.nodes)) {
			System.out.println("1");
		}else {
			System.out.println("0");
		}
	}
	
	
	public boolean checkSubsets(ArrayList<Node> setOfNodes) { //returns false if not reliable
		if(setOfNodes.size()>1){
			ArrayList<Node> copyOfSubset= (ArrayList<Node>) setOfNodes.clone();
			for(Node n : setOfNodes){
				copyOfSubset.remove(n);
				if(checkIfReliable(copyOfSubset)){
					if(!checkSubsets(copyOfSubset)){
						return false;
					}
				} else{
					return false;
				}
				copyOfSubset.add(n) ;
			}
		}
		return true;
	}

	public boolean checkIfReliable(ArrayList<Node> setOfNodes){
		double probability=1;
		for(Node n: setOfNodes){
			for(int nodeNumber: n.edges.keySet() ){
				if(!setOfNodes.contains(new Node(nodeNumber))){
					probability*=n.edges.get(nodeNumber).failure;
				}
			}
		}
		if(probability>0.1){
			return false;
		}else{
			return true;
		}
	}
	
	public class Graph{
		private ArrayList<Node> nodes;
		private int numberOfNodes;
		Graph(int numberNodes){
			nodes= new ArrayList<Node>(numberNodes);
			this.numberOfNodes=numberNodes;
			for(int i=0; i< numberOfNodes; i++) {
				nodes.add(new Node(i));
			}
		}
		
		public void addEdge(int startNode, int endNode, double failure) {
			Edge temp= new Edge(failure);
			this.nodes.get(startNode).edges.put(endNode, temp);
			this.nodes.get(endNode).edges.put(startNode, temp);
		}
		
		
		
		
	}
	
	public class Node{
		private int nodeNumber;
		private HashMap<Integer, Edge> edges;
		
		public Node(int nodeNumber) {
			this.nodeNumber=nodeNumber;
			this.edges=new HashMap<Integer, Edge>();
		}

		@Override
		public boolean equals(Object obj) {
			Node other= (Node) obj;
			if(this.nodeNumber==other.nodeNumber) {
				return true;
			}
			return false;
		}
		
	}
	
	public class Edge{
		private double failure;
		
		public Edge(double failure) {
			this.failure=failure;
		}
	}

}
