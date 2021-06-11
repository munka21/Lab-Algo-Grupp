import java.util.*;


class fastnfurios{
    public static void main (String [] args) {
        Scanner scanner = new Scanner(System.in);
        int dim = scanner.nextInt();
        int [] tree = new int [dim];
        Arrays.fill(tree, 0);

        ArrayList <node>  nodes = new ArrayList <node> ();
        ArrayList <edge> edges = new ArrayList<edge>();

        for (int i = 0; i < dim; i++) {
            nodes.add(new node(i, scanner.nextInt(), scanner.nextInt()));
        }
        for(int i = 0; i<dim; i++){
            for(int j = i+1; j<dim;j++){
                edges.add(new edge(nodes.get(i).dist(nodes.get(j)),nodes.get(i).getId(),nodes.get(j).getId()));
            }
        }


        Collections.sort(edges);
        //System.out.println(edges.toString());
        
        int mst = 0;
        for (int i = 0; i < edges.size(); i++){
            edge e = edges.get(i);
            if(tree[e.getU()]== 0 || tree[e.getV()]==0)
            {
                mst+= e.getC();
                tree[e.getU()] = 1;
                tree[e.getV()] = 1;
            }
        }

        System.out.println(2*mst);
    }
}



class node {
    int id;
    int x;
    int y;

    public node(int id, int x, int y){
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int dist(node v){
        return Math.abs(x - v.getX())+Math.abs(y-v.getY());
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return id + " " + x + " " +y +"\n";
    }
}

class edge implements Comparable{
    int c;
    int u;
    int v;

    public edge(int c, int u, int v) {
        this.c = c;
        this.u = u;
        this.v = v;
    }

    public int getU() {
        return u;
    }

    public int getV() {
        return v;
    }

    public int getC() {
        return c;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return c + " " + u + " " + v +"\n";
    }

    @Override
    public int compareTo(Object o) {
        edge other = (edge) o;
        return Integer.compare(this.c, other.getC());
    }
}