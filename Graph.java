import java.util.*;
import java.lang.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Graph {
    int V;
    int E;
    Edge edge[];

    class Edge implements Comparable<Edge> {
        int src;
        int dest;
        int weight;
        public int compareTo(Edge compareEdge) {
            return this.weight - compareEdge.weight;
        }
    }
    class subset {
        int parent;
        int rank;
    }

    Graph(int v, int e) {
        V = v;
        E = e;
        edge = new Edge[E];
        for (int i = 0; i < e; i++) {
            edge[i] = new Edge();
        }
    }

    int find(subset subsets[], int i) {
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    void Union(subset subsets[], int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank) {
            subsets[xroot].parent = yroot;
        } else if (subsets[xroot].rank > subsets[yroot].rank) {
            subsets[yroot].parent = xroot;
        }
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    Edge[] KruskalMST() {
        Edge result[] = new Edge[V];
        int e = 0;
        int i = 0;
        Arrays.sort(edge);
        subset subsets[] = new subset[V];
        for (i = 0; i < V; i++) {
            result[i] = new Edge();
            subsets[i] = new subset();
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }
        i = 0;
        while (e < V - 1) {
            Edge next_edge = edge[i++];
            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);

            if (x != y) {
                result[e++] = next_edge;
                Union(subsets, x, y);
            }
        }
        return result;
    }

    void printResult(Edge [] result, int e) {
        System.out.println("Following are the edges in " + "the constructed MST");
        int minimumCost = 0;
        for (int i = 0; i < e; i++) {
            System.out.println(result[i].src + " --(" + result[i].weight+ ")--> " + result[i].dest);
            minimumCost += result[i].weight;
        }
        System.out.println("Minimum Cost Spanning Tree " + minimumCost);
    }

    void k_islands (int k, Edge[] MST, int V){
        int index = MST.length - k;
        System.out.print(MST[index].weight);//nur kleinste Element ausgeben
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("D:\\test.txt"));
        //BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] Line = bf.readLine().split(" ");
        int V = Integer.parseInt(Line[0]);
        Line = bf.readLine().split("");
        int k = Integer.parseInt(Line[0]);
        Line = bf.readLine().split(" ");
        int E = Integer.parseInt(Line[0]);
        Graph graph = new Graph(V, E);

        for (int i = 0; i < E; i++){
            Line = bf.readLine().split(" ");
            int src = Integer.parseInt(Line[0]);
            int dest = Integer.parseInt(Line[1]);
            int weight = Integer.parseInt(Line[2]);

            graph.edge[i].src = src;
            graph.edge[i].dest = dest;
            graph.edge[i].weight = weight;
        }
        Edge[] MST = graph.KruskalMST();
        graph.printResult(MST, V-1);
        graph.k_islands(k,MST, V-1);
    }
}