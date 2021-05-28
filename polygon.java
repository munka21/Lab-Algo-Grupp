import java.util.Scanner;
import java.util.Locale;

class polygon
{
    public static void main(String [] args) {

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        int n = scanner.nextInt();
        double b = n;

        int[][] coordinates = new int[n][2];

        for (int i = 0; i < n; i++) {
            coordinates[i][0] = scanner.nextInt();
            coordinates[i][1] = scanner.nextInt();
        }

        double sum1 = 0;
        double sum2 = 0;

        for (int i = 0; i < n; i++){

            int x1 = coordinates[i][0];
            int y1 = coordinates[i][1];
            int x2;
            int y2;

            if(i==n-1){
                x2 = coordinates[0][0];
                y2 = coordinates[0][1];
            }
            else{
                x2 = coordinates[i+1][0];
                y2 = coordinates[i+1][1];
            }

            sum1+= x1*y2;
            sum2+= x2*y1;

            int m = (y2-y1);///(x2-x1);
            int t = (y1*x2-y2*x1);///(x2-x1);

            if(x1<x2){

                for(int point = x1+1; point < x2; point++) {
                    int y = m * point + t;
                    if (y%(x2-x1)==0) b++;
                }
            }
            else if (x1>x2){
                for(int point = x2+1; point < x1; point++) {
                    int y = m * point + t;

                    if (y%(x1-x2)==0) {
                        b++;
                        //System.out.println(point + " "+ y);
                    }
                }
            }
            else if (x1 == x2){
                if(y1 > y2) b+= y1 -y2 -1;
                else if(y2>y1) b+= y2 -y1 -1;
            }
        }

        double erg = (sum2 - sum1)/2;
        if(erg<0)erg = -erg;
        int points = (int)(erg+(b/2))+1;
        System.out.println(points);

    }
}