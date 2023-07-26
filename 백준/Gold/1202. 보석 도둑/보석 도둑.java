import java.io.*;
import java.util.*;

public class Main {

    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));

    static int readInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException {
        int n = readInt();
        int k = readInt();
        List<Jewel> jewels = new ArrayList<>(n);
        List<Integer> bags = new ArrayList<>(k);

        for (int i = 0; i < n; i++) {
            jewels.add(new Jewel(readInt(), readInt()));
        }

        for (int i = 0; i < k; i++) {
            bags.add(readInt());
        }

        jewels.sort(Comparator.comparingInt(a -> a.w));
        Collections.sort(bags);

        PriorityQueue<Integer> pq = new PriorityQueue<>(n, Comparator.reverseOrder());
		long res = 0;
        int i = 0;

        for (int bag : bags) {
            while (i < n && bag >= jewels.get(i).w) {
                pq.add(jewels.get(i++).v);
            }

            if (!pq.isEmpty()) {
                res += pq.poll();
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.append(String.format("%d", res));
        bw.flush();
        bw.close();
    }
}

class Jewel {
    int w;
    int v;

    Jewel(int w, int v) {
        this.w = w;
        this.v = v;
    }
}