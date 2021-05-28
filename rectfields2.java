import java.util.*;
import java.io.*;


class rectfields2 {
    public static void main(String[] args) {

        myscanner scanner = new myscanner();
        int n = 0;
        try {
            n = IntegerValueOf(scanner.next());
        } catch (IOException e) {
            e.printStackTrace();
        }

        HashMap lines = new HashMap();

        int erg = 0;
        for (int i = 0; i < n; i++) {
            int[] pair = new int[i];
            Arrays.fill(pair, 0);
            for (int j = 0; j < n; j++) {
                char ca = 0;
                try {
                    ca = scanner.next();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                if (ca == '1') {
                    lines.put((i + "#" + j), 1);
                    for (int k = 0; k < i; k++) {

                        //System.out.println(lines.keySet());
                        if (lines.containsKey((k + "#" + j))){
                            pair[k]++;

                        }

                    }
                }
            }

            for(int x = 0; x< i; x++) {

                if (pair[x] >= 2) {
                    erg += (pair[x] - 1) * pair[x];
                }
            }
        }
        erg = erg/2;
        System.out.println((int)erg);
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
}




