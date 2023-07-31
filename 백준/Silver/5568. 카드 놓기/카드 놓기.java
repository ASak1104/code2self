import java.io.*;
import java.util.HashSet;
import java.util.Set;

class Main {

    private static final StreamTokenizer sttk = new StreamTokenizer(new InputStreamReader(System.in));
    private static final int[] cards = new int[10];
    private static final Set<Integer> set = new HashSet<>();
    private static int n;
    private static int k;

    private static int readInt() throws IOException {
        sttk.nextToken();

        return (int) sttk.nval;
    }

    public static void main(String[] args) throws IOException {
        n = readInt();
        k = readInt();

        for (int i = 0; i < n; i++) {
            cards[i] = readInt();
        }

        travel(0, 0, 0);

        System.out.println(set.size());
    }

    static void travel(int prev, int visit, int count) {
        if (count == k) {
            set.add(prev);
            return;
        }

        for (int i = 0; i < n; i++) {
            int mask = (1 << i);

            if ((visit & mask) == mask) continue;

            int w = cards[i] >= 10 ? 100 : 10;

            travel(prev * w + cards[i], visit | mask, count + 1);
        }
    }
}