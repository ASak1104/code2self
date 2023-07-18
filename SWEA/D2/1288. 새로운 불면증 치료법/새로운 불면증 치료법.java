import java.io.*;

public class Solution {
    private static final StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));

    private static int readInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = readInt();
        int end = (1 << 10) - 1;

        for (int tc = 1; tc <= T; tc++) {
            int n = readInt();
            int k = 0;
			int mask = 0;

            while (mask < end) {
                int target = ++k * n;

                while (target > 0) {
                    mask |= 1 << (target % 10);
                    target /= 10;
                }
            }

            bw.append(String.format("#%d %d\n", tc, k * n));
        }

        bw.flush();
        bw.close();
    }
}