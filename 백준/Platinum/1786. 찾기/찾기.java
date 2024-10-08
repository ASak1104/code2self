import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        String T = br.readLine();
        String P = br.readLine();
        KMP kmp = new KMP(P);
        List<Integer> matched = kmp.match(T);

        sb.append(matched.size()).append(System.lineSeparator());
        matched.forEach(i -> sb.append(i).append(' '));

        System.out.println(sb);
    }

}

class KMP {

    private final char[] p;
    private final int[] fails;
    private final int m;

    public KMP(String pattern) {
        p = pattern.toCharArray();
        m = p.length;
        fails = new int[m];

        int j = 0;

        for (int i = 1; i < p.length; i++) {
            while (j > 0 && p[i] != p[j]) {
                j = fails[j - 1];
            }

            if (p[i] == p[j]) {
                j++;
            }

            fails[i] = j;
        }
    }

    public List<Integer> match(String text) {
        List<Integer> matched = new ArrayList<>();
        char[] t = text.toCharArray();
        int j = 0;

        for (int i = 0; i < t.length; i++) {
            while (j > 0 && t[i] != p[j]) {
                j = fails[j - 1];
            }

            if (t[i] != p[j]) {
                continue;
            }

            if (j == m - 1) {
                matched.add(i - j + 1);
                j = fails[j];
            } else {
                j++;
            }
        }

        return matched;
    }

}
