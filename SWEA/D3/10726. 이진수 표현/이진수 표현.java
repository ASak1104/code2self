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

        for (int tc = 1; tc <= T; tc++) {
            int n = readInt();
            int m = readInt();
            int mask = (1 << n) - 1;

            bw.append(String.format("#%d %s\n", tc, (m & mask) == mask ? "ON" : "OFF"));
        }

        bw.flush();
        bw.close();
    }
}