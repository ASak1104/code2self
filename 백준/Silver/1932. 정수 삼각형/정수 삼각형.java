import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static StringTokenizer st;
    static int[][] triangle = new int[501][501];
    static int n, res = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int r = 1; r <= n; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 1; c <= r; c++) {
                triangle[r][c] = nextInt() + Math.max(triangle[r - 1][c - 1], triangle[r - 1][c]);
            }
        }

        for (int c = 1; c <= n; c++) {
            res = Math.max(res, triangle[n][c]);
        }

        System.out.println(res);
        br.close();
    }

    static int nextInt() {
        return Integer.parseInt(st.nextToken());
    }
}