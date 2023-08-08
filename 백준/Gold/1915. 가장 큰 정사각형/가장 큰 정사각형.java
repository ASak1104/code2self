import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int[][] table;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        table = new int[n + 1][m + 1];

        for (int r = 1; r <= n; r++) {
            String row = br.readLine();

            for (int c = 1; c <= m; c++) {
                table[r][c] = row.charAt(c - 1) - '0';
            }
        }

        int res = 0;

        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= m; c++) {
                if (table[r][c] == 0) continue;

                table[r][c] += Math.min(table[r - 1][c - 1], Math.min(table[r - 1][c], table[r][c - 1]));

                res = Math.max(res, table[r][c]);
            }
        }

        System.out.println(res * res);
        br.close();
    }
}