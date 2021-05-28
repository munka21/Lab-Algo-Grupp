import java.util.*;
import java.io.*;


class rectfields{
    public static void main(String [] args) {

        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        int n = 0;
        try  {
            n = Integer.valueOf(scanner.readLine());
        }
         catch (IOException e) {
             e.printStackTrace();
         }

        char [][] lines = new char [n][n];


        for (int i = 0; i < n; i++){
            try {
                lines[i] = scanner.readLine().toCharArray();
            }

            catch (IOException e) {
                e.printStackTrace();
            }
        }
        Arrays.sort(lines,  Arrays::compare);

        int erg = 0;

        int k = 0;
        for (int i = 0; i < n; i++){

            while(k < n && lines[i][k]=='1') {
                k++;
            }

            int [] pair = new int[n-i-1];
            Arrays.fill(pair, k);

            for(int l = k+1; l<n; l++) {
                if (lines[i][l] == '1') {
                    for (int j = i + 1; j < n; j++) {
                        if (lines[j][l] == '1') pair[n-j-1]++;
                    }
                }
            }

            for(int x = 0; x<n-i-1; x++) {

                if (pair[x] >= 2) {
                    erg += (pair[x] - 1) * pair[x];
                }
            }
        }
        erg = erg/2;
        System.out.println((int)erg);
    }
}