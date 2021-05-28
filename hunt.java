import java.io.*;
import java.util.*;


class hunt {
    public static void main(String[] args) {
        myscanner scanner = new myscanner();
        int n = Integer.parseInt(scanner.next());
        int m = Integer.parseInt(scanner.next());


        int erg = 0;
        int[][] grid = new int[n][m];

        grid[0][0] = Integer.parseInt(scanner.next());
        for (int j = 1; j < m; j++) {
            grid[0][j] = Integer.parseInt(scanner.next()) + grid[0][j - 1];
        }

        for (int i = 1; i < n; i++) {
            grid[i][0] = Integer.parseInt(scanner.next()) + grid[i - 1][0];
            for (int j = 1; j < m; j++) {
                if (grid[i - 1][j] < grid[i][j - 1]) {
                    grid[i][j] = Integer.parseInt(scanner.next()) + grid[i][j - 1];
                } else {
                    grid[i][j] = Integer.parseInt(scanner.next()) + grid[i - 1][j];
                }
            }

        }
        System.out.println(grid[n - 1][m - 1]);

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

    int nextInt() {
        return Integer.parseInt(next());
    }
}
