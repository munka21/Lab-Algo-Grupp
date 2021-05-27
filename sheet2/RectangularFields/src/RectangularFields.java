import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class RectangularFields {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
    	int n= Integer.parseInt(bi.readLine());
    	
    	HashMap<TreePair, Integer> treePairCounts =new HashMap<TreePair, Integer>();
    	
    	for(int i=0; i<n; i++) {
    		String [] temp=bi.readLine().split("");
    		
    	}
	}

}

class TreePair{
	private int indexFirstTree;
	private int indexSecondTree;
	private int hashvalue;
	
	public TreePair(int indexFirstTree, int indexSecondTree) {
		this.indexFirstTree=indexFirstTree;
		this.indexSecondTree=indexSecondTree;
		hashvalue=((indexFirstTree+"")+(indexSecondTree+"")).hashCode();
	}
	public boolean equals (final Object obj)
    {
        if (obj instanceof TreePair)
        {
        	TreePair treePair = (TreePair) obj;
            return ((indexFirstTree==treePair.getIndexFirstTree()) && (indexSecondTree==treePair.getIndexSecondTree()));
        }
        else
            return false;
    }
	public int hashCode()
    {
        return hashvalue;
    }
	public int getIndexFirstTree() {
		return indexFirstTree;
	}
	public void setIndexFirstTree(int indexFirstTree) {
		this.indexFirstTree = indexFirstTree;
	}
	public int getIndexSecondTree() {
		return indexSecondTree;
	}
	public void setIndexSecondTree(int indexSecondTree) {
		this.indexSecondTree = indexSecondTree;
	}
	
	
}
