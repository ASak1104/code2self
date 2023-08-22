import java.io.*;
import java.util.StringTokenizer;

class Main {
    static int[][] costs;
    static int[] cumSum;
    static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int totalCase = Integer.parseInt(br.readLine());

        while (totalCase-- > 0) {
            k = Integer.parseInt(br.readLine());
            costs = new int[k + 1][k + 1];
            cumSum = new int[k + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= k; i++) {
                cumSum[i] = cumSum[i - 1] + Integer.parseInt(st.nextToken());
            }

            bw.append(String.valueOf(solve()));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int solve() {
        for (int size = 1; size < k; size++) {
            for (int s = 1; s <= k - size; s++) {
                int e = s + size;
                int cost = Integer.MAX_VALUE;

                for (int mid = s; mid < e; mid++) {
                    cost = Math.min(cost, costs[s][mid] + costs[mid + 1][e]);
                }

                costs[s][e] = cost + cumSum[e] - cumSum[s - 1];
            }
        }

        return costs[1][k];
    }
}