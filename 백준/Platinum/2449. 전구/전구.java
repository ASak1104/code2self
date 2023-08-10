import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int[][] dp;
    static int[] lights;
    static int n, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        lights = new int[n];

        for (int i = 0; i < n; i++) {
            lights[i] = Integer.parseInt(st.nextToken());

            if (i > 0 && lights[i] == lights[i - 1]) {
                n--;
                i--;
            }
        }

        dp = new int[n][n];

        for (int k = 1; k < n; k++) {
            for (int s = 0; s < n - k; s++) {
                int e = s + k;
                int min = Integer.MAX_VALUE;

                for (int mid = s; mid < e; mid++) {
                    int temp = dp[s][mid] + dp[mid + 1][e];

                    if (lights[s] != lights[mid + 1]) temp++;

                    min = Math.min(min, temp);
                }

                dp[s][e] = min;
            }
        }

        System.out.println(dp[0][n - 1]);
        br.close();
    }
}