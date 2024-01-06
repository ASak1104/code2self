import java.util.Arrays;

class Solution {

    static final int MAX = (int) 1e9;

    static int[][] dists;

    public int networkDelayTime(int[][] times, int n, int k) {
        dists = new int[n + 1][n + 1];

        for (int u = 1; u <= n; u++) {
            Arrays.fill(dists[u], MAX);
            
            dists[u][0] = 0;
            dists[u][u] = 0;
        }

        for (int[] time : times) {
            dists[time[0]][time[1]] = time[2];
        }

        floydWarshall(n);

        return getDelayTime(k);
    }

    void floydWarshall(int n) {
        for (int k = 1; k <= n; k++) {
            for (int u = 1; u <= n; u++) {
                for (int v = 1; v <= n; v++) {
                    dists[u][v] = Math.min(dists[u][v], dists[u][k] + dists[k][v]);
                }
            }
        }
    }

    int getDelayTime(int k) {
        int max = -1;

        for (int w : dists[k]) {
            if (w == MAX) {
                return -1;
            }

            max = Math.max(max, w);
        }

        return max;
    }

}
