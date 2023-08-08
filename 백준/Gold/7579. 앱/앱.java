import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static StringTokenizer st;
    static int[][] dp;
    static int[] memories, costs;
    static int n, m, totalCost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = nextInt();
        m = nextInt();

        st = new StringTokenizer(br.readLine());
        memories = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            memories[i] = nextInt();
        }

        st = new StringTokenizer(br.readLine());
        costs = new int[n + 1];
        totalCost = 0;

        for (int i = 1; i <= n; i++) {
            costs[i] = nextInt();
            totalCost += costs[i];
        }

        dp = new int[n + 1][totalCost + 1];

        for (int i = 1; i <= n; i++) {
            int memory = memories[i];
            int cost = costs[i];

            for (int j = 0; j <= totalCost; j++) {
                if (cost > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost] + memory);
                }
            }
        }

        int res = -1;

        for (int i = 0; i <= totalCost; i++) {
            if (dp[n][i] >= m) {
                res = i;
                break;
            }
        }

        System.out.println(res);
        br.close();
    }

    static int nextInt() {
        return Integer.parseInt(st.nextToken());
    }
}