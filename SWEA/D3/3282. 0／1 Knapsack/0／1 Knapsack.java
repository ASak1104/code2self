import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Solution {
    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));

    static int readInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCount = readInt();

        for (int tc = 1; tc <= testCount; tc++) {
            int n = readInt();
            int k = readInt();

            List<Item> bag = new ArrayList<>(n);

            for (int i = 0; i < n; i++) {
                bag.add(new Item(readInt(), readInt()));
            }

            int[][] dp = new int[n + 1][k + 1];

            for (int i = 1; i <= n; i++) {
                Item item = bag.get(i - 1);

                for (int w = 1; w <= k; w++) {
                    if (w < item.v) {
                        dp[i][w] = dp[i - 1][w];
                    } else {
                        dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - item.v] + item.c);
                    }
                }
            }

            bw.append(String.format("#%d %d\n", tc, dp[n][k]));
        }

        bw.flush();
        bw.close();
    }
}

class Item {
    int v;
    int c;

    Item(int v, int c) {
        this.v = v;
        this.c = c;
    }
}