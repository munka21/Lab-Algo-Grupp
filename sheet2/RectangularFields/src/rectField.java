
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;

class rectfield{
    public static void main(String [] args) throws NumberFormatException, IOException {

        //einlesen
    	BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
    	int n= Integer.parseInt(bi.readLine());

        ArrayList<BigInteger> lines  = new ArrayList<BigInteger>(n);


        for (int i = 0; i < n; i++){
        	lines.add(new BigInteger(bi.readLine()));
        }

        int erg = 0;
        //vergleiche alle Zeilen paarweise
        for (int i = 0; i < n; i++){
            for (int j = i+1; j < n; j++){

                //An Stellen an denen beide Zeilen eine 1 haben, steht dann eine 2 im String, je zwei 2en ergeben ein Rechteck
                
            	String s= lines.get(i).add(lines.get(j)).toString();
                //Zählt die 2en im String
                long count = s.chars().filter(ch -> ch == '2').count();

                if(count>=2){
                    //Anzahl der Paare von 2en (Gaussche Summenformel, halbieren tue ich unten gesamt)
                    erg += (count-1)*count;
                }
            }
        }
        erg = erg/2;
        System.out.println((int)erg);
    }

}