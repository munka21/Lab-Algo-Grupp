import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class PrimarySchool {

	public static void main(String[] args) throws IOException {
		BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
		int numberEntries =Integer.parseInt(bi.readLine());
		StringTokenizer tokenizer= new StringTokenizer(bi.readLine(), " ");
		
		HashSet<StudentName> nameSet=new HashSet<StudentName>();
		while (tokenizer.hasMoreTokens()) {
			nameSet.add(new StudentName(tokenizer.nextToken()));
		}
		
		System.out.print(nameSet.size());

	}

}

 class StudentName{
	 private HashMap<Character, Integer> charFrequencies;
	 private int hashvalue;
	 

	StudentName(String name){
			hashvalue=0;
		 charFrequencies = new HashMap<Character, Integer>();
		 for (char ch : name.toCharArray()) { 
			 charFrequencies.put(ch, charFrequencies.getOrDefault(ch, 0) + 1);
		 }
		 for(Character c: charFrequencies.keySet()) {
			 hashvalue += c.hashCode();
		 }
	 }
	
	public HashMap<Character, Integer> getCharFrequencies() {
		return charFrequencies;
	}
	 @Override
	 public boolean equals(Object obj) {
		 if(obj instanceof StudentName) {
			 StudentName name2=(StudentName) obj;
			 if(this.getCharFrequencies().size()==name2.getCharFrequencies().size()) {
				 if(this.getCharFrequencies().keySet().equals(name2.getCharFrequencies().keySet())) {
					 for(char k : this.getCharFrequencies().keySet()) {
						 if(! (this.getCharFrequencies().get(k)==name2.getCharFrequencies().get(k))) {
							 return false;
						 }
					 }
					 return true;
				 }
			 }
			 
			 return false;
		 } else {
			 return false;
		 }
		 
	 }
	 public int hashCode()
	    {
	        return hashvalue;
	    }
 }
