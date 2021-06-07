import java.util.Scanner;
import java.util.Arrays;

class oil{
    public static void main (String [] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[][] sums = new int [n+1][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                sums[i+1][j] = sums [i][j]+ scanner.nextInt();
            }
        }
        int max = -1;
        int min = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++){
            for (int j = i; j < n; j++) {
                int sum = 0;

                for(int k = 0; k< n; k++)
                {
                    sum += sums[j+1][k]-sums[i][k];
                    if(sum<0){
                        if(min < sum){
                            min = sum;
                        }
                        sum = 0;
                    }
                    else if(max< sum){

                        max = sum;
                    }

                }
            }

        }
        System.out.println(max == -1? min : max);


    }
}