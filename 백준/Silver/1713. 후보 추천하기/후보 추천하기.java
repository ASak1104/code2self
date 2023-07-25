import java.io.*;
import java.util.Comparator;
import java.util.LinkedList;

public class Main {

    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));

    static int readInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException {
        int n = readInt();
        int k = readInt();
        int[] votes = new int[101];
        int[] times = new int[101];
        LinkedList<Integer> deque = new LinkedList<>();

        for (int t = 0; t < k; t++) {
            int vote = readInt();

            votes[vote]++;

            if (votes[vote] > 1) continue;

            times[vote] = t;

            if (deque.size() < n) {
                deque.addLast(vote);
                continue;
            }

            deque.sort((a, b) -> {
                if (votes[a] == votes[b]) {
                    return times[a] - times[b];
                }

                return votes[a] - votes[b];
            });

            votes[deque.removeFirst()] = 0;
            deque.addLast(vote);
        }

        deque.sort(Comparator.naturalOrder());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int v : deque) {
            bw.append(String.format("%d ", v));
        }

        bw.flush();
        bw.close();
    }
}