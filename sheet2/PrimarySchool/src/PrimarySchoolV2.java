import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class PrimarySchoolV2 {
	
	public static void main(String[] args) throws IOException {
		 HashMap<Character, Integer> charToPrime;
		BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
		int numberEntries =Integer.parseInt(bi.readLine());
		TreeSet<BigInteger> nameSet=new TreeSet<BigInteger>();
		if(numberEntries>0) {
			charToPrime =new HashMap<Character, Integer>();
			charToPrime.put('a',2);
			charToPrime.put('b',3);
			charToPrime.put('c',5);
			charToPrime.put('d',7);
			charToPrime.put('e',11);
			charToPrime.put('f',13);
			charToPrime.put('g',17);
			charToPrime.put('h',19);
			charToPrime.put('i',23);
			charToPrime.put('j',29);
			charToPrime.put('k',31);
			charToPrime.put('l',37);
			charToPrime.put('m',41);
			charToPrime.put('n',43);
			charToPrime.put('o',53);
			charToPrime.put('p',59);
			charToPrime.put('q',61);
			charToPrime.put('r',67);
			charToPrime.put('s',71);
			charToPrime.put('t',73);
			charToPrime.put('u',79);
			charToPrime.put('v',83);
			charToPrime.put('w',97);
			charToPrime.put('x',101);
			charToPrime.put('y',103);
			charToPrime.put('z',107);
			StringTokenizer tokenizer= new StringTokenizer(bi.readLine(), " ");
			while (tokenizer.hasMoreTokens()) {
				String s=tokenizer.nextToken();
				BigInteger temp= BigInteger.ONE;
				for( char c : s.toCharArray()) {
					temp=temp.multiply(BigInteger.valueOf(charToPrime.get(c)));
				}
				if(!nameSet.contains(temp)) {
					nameSet.add(temp);
				}
			}
		}
		
		
		
		
		System.out.print(nameSet.size());

	}
	


	public static boolean isAnagram(ArrayList<String> list, String s) {
		
		
		for(String name : list) {
			if(isAnagram(name,s)) {
				return true;
			}
		}
		return false;
	}
	
public static boolean isAnagram(String firstString, String s) {
		
		
		
			int[] array = new int[26];
			for(char c:firstString.toCharArray()) {
				array[c-'a']++;
			}
			for(char c:s.toCharArray()) {
				array[c-'a']--;
			}
			for(int i:array) {
				if(!(i==0)) {
					return false;
				}
			}
			return true;
		
	}



}
