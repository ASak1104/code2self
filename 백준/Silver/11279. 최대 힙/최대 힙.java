import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

class Main {

    static StreamTokenizer sttk = new StreamTokenizer(new InputStreamReader(System.in));

    static int readInt() throws IOException {
        sttk.nextToken();
        return (int) sttk.nval;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int n = readInt();

        while (n-- > 0) {
            int k = readInt();

            if (k == 0) {
                if (!pq.isEmpty()) {
                    bw.append(Integer.toString(pq.poll()));
                } else {
                    bw.append('0');
                }
                bw.newLine();
            } else {
                pq.add(k);
            }
        }

        bw.flush();
        bw.close();
    }
}