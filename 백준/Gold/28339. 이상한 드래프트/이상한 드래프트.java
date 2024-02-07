import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] positions, scores;
    static int T, N, K;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            positions = new int[N];
            scores = new int[N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                positions[i] = Integer.parseInt(st.nextToken());
                scores[i] = Integer.parseInt(st.nextToken());
            }

            System.out.println(idealDraft());
        }
    }

    private static int idealDraft() {
        int max = 0;
        PriorityQueue<Integer>[] maxScores = new PriorityQueue[10];

        for (int i = 1; i <= 9; i++) {
            maxScores[i] = new PriorityQueue<>(Comparator.comparingInt(a -> -scores[a]));
        }

        for (int i = 0; i < N; i++) {
            maxScores[positions[i]].add(i);

            int left = i - K + 1;
            int score = 0;

            for (int pos = 1; pos <= 9; pos++) {
                PriorityQueue<Integer> pq = maxScores[pos];

                while (!pq.isEmpty() && pq.peek() < left) {
                    pq.poll();
                }

                if (pq.isEmpty()) {
                    break;
                }

                score += scores[pq.peek()];

                if (pos == 9) {
                    max = Math.max(max, score);
                }
            }
        }

        return max;
    }

}
