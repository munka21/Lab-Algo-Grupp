import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class tyrion {

    public static void main(String[] args) {

        myscanner s = new myscanner();

        int n = s.nextInt();

        HashMap<String, Integer> variables = new HashMap<>();
        int [][] equations = new int [n][n+1];

        int k = 0;
        for(int i = 0; i<n; i++){
            Arrays.fill(equations[i], 0);
            String [] line = s.nextline().split(" ");
            for(int j = 0; j < line.length -1; j++){
                if(!(variables.containsKey(line[j]))){
                    variables.put(line[j], k);
                    k++;

                }
                equations[i][variables.get(line[j])] = 1;
            }
            if (line[line.length-1].equals("odd")) {
                equations[i][n] = 1;
            }
            else{
                equations[i][n] = 0;
            }

        }


        for(int i = 0; i < n; i++){
            int max = i;
            for (k = i; k < n; k++){
                if(equations[k][i]==1){
                    max = k;
                    break;
                }
            }

            equations = swap(equations, n, i, max);

            for (int u = i + 1; u < n; u++) {
                equations = diff(equations, n, i, u);
            }


        }
        int erg = 0;
        for(int i = 0; i< n-1; i++){
            erg += equations[n-1-i][n];

            for(int j = i+1; j < n; j++){
                if(equations[n-1-j][n-1-i]==1){
                    equations[n - 1 - j][n] = Math.abs(equations[n - 1 - j][n] - equations[n - 1 - i][n]);
                }
            }


        }
        erg+= equations[0][n];
        System.out.println(erg);



    }

    public static int[][] swap(int [][] in, int n,int i, int j){
        int[][] equations = in;
        int [] temp = equations[i];
        equations[i] = equations[j];
        equations[j] = temp;
        return equations;
    }

    public static int[][] diff(int [][] in, int n, int i, int j){
        int[][] equations = in;
        if(equations[j][i]==1) {
            for (int k = i; k < n + 1; k++) {
                equations[j][k] = Math.abs(equations[j][k] - equations[i][k]);
            }
        }
        return equations;
    }


}



class myscanner{
    BufferedReader br;
    StringTokenizer st;

    public myscanner() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }
    String nextline (){
        String st = "";
        try {
             st = br.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return st;

    }

    int nextInt() {
        return Integer.parseInt(next());
    }
}