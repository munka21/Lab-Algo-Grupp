import java.util.*;
import java.io.*;


class duckburg {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int k = scanner.nextInt();
        int n = scanner.nextInt();

        int[][] D = new int[n][k];

        int[] x = new int[n];

        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
        }
        Arrays.sort(x);

        for (int i = 0; i < n; i++) {
            D[i][0] = last(i, 0, x);
        }

        for (int i = 1; i < n; i++) { //highest index in last cluster & largest index
            for (int j = 1; j < i; j++) {// lowest index in last cluster
                for (int m = 1; m < k; m++) {//number of clusters-1
                    if (i == 0) { //only one number
                        D[i][m] = 0;
                    } else if (m < i) {
                        int min = Integer.MAX_VALUE;
                        int dist = last(i, j, x);

                        if (D[j - 1][m - 1] + dist < min) {
                            min = D[j - 1][m - 1] + dist;
                        }
                        D[i][m] = min;

                    } else {
                        D[i][m] = 0;
                    }

                }


            }

        }
        System.out.println(n*25-D[n-1][k-1]);


    }

    public static int last(int i, int j, int[] x) {
        int dist = 0;
        int mu = 0;

        for (int l = 0; l <= i - j; l++) {
            mu += x[j + l];
        }
        mu = mu / (i - j + 1);

        for (int l = 0; l < i - j + 1; l++) {
            dist += Math.abs(x[j + l] - mu);
        }

        return dist;

    }
}

/*class myscanner{
    BufferedReader br;
    StringTokenizer st;

    public myscanner() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.read());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }
}*/




