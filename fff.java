import java.util.Scanner;
import java.util.Arrays;;

class fff {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int [] cover = new int [n];
        Arrays.fill(cover, 0);
        int erg = 0;

        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            if (!(cover[a] == 1 || cover [b] == 1)){
                cover[a] = 1;
                cover[b] = 1;
                erg += 2;
            }
        }
        System.out.println(erg);
    }
}