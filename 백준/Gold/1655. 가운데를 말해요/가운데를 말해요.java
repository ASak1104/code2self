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
        PriorityQueue<Integer> leftPQ = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> rightPQ = new PriorityQueue<>();
        int n = readInt();

        while (n-- > 0) {
            leftPQ.add(readInt());

            while (leftPQ.size() > rightPQ.size()) {
                rightPQ.add(leftPQ.poll());
            }

            while (rightPQ.size() > leftPQ.size()) {
                leftPQ.add(rightPQ.poll());
            }

            bw.append(Integer.toString(leftPQ.peek()));
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}