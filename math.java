import java.util.Scanner;

class math{



    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            boolean done = false;

            for (int x= -(int)Math.round(Math.sqrt(w)); x < u; x++) {
                for (int y = x+1; y < u; y++) {
                    if ((x * x + y * y + (u - x - y) * (u - x - y) == w)&&(u - x - y)>y && (x*y*(u-x-y)==v))  {
                        System.out.println(x + " " + y + " " + (u - x - y));
                        done = true;
                        break;
                    }
                }
                if (done) break;
            }
            if (!done) {
                System.out.println("empty set");
            }
        }
    }
}