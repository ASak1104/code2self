import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class Main {

    private static Set<String>[] memo;
    private static char[] seq;
    private static int k = 0;
    private static String res = "-1";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        seq = st.nextToken().toCharArray();
        k = Integer.parseInt(st.nextToken());
        memo = new Set[k + 1];

        for (int i = 0; i <= k; i++) {
            memo[i] = new HashSet<>();
        }

        travel(0);

        System.out.println(res);
        br.close();
    }

    static void travel(int count) {
        if (seq[0] == '0') return;

        String s = new String(seq);

        if (memo[count].contains(s)) return;

        memo[count].add(s);

        if (count == k) {
            if (res.compareTo(s) < 0) {
                res = s;
            }

            return;
        }

        for (int i = 0; i < seq.length; i++) {
            for (int j = 0; j < seq.length; j++) {
                if (i == j) continue;

                swap(i, j);
                travel(count + 1);
                swap(i, j);
            }
        }
    }

    static void swap(int i, int j) {
        char temp = seq[i];

        seq[i] = seq[j];
        seq[j] = temp;
    }
}