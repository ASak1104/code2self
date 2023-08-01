import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

class Main {

    static StreamTokenizer sttk = new StreamTokenizer(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int m;
    static int[] seq;
    static int[] ans;

    static int readInt() throws IOException {
        sttk.nextToken();
        return (int) sttk.nval;
    }

    public static void main(String[] args) throws IOException {
        n = readInt();
        m = readInt();
        seq = new int[n];
        ans = new int[m];

        for (int i = 0; i < n; i++) {
            seq[i] = readInt();
        }

        Arrays.sort(seq);

        travel(0, 0, 0);

        System.out.println(sb);
    }

    static void travel(int start, int count, int visit) {
        if (count == m) {
            for (int v : ans) {
                sb.append(v);
                sb.append(' ');
            }
            sb.append('\n');

            return;
        }

        int prev = -1;

        for (int i = start; i < n; i++) {
            if (seq[i] == prev) continue;

            int mask = 1 << i;

            if ((visit & mask) == mask) continue;

            ans[count] = seq[i];
            prev = seq[i];

            travel(i + 1, count + 1, visit | mask);
        }
    }
}