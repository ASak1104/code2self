import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static Lecture[] lectures;
    static int n, count = 1;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        lectures = new Lecture[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            lectures[i] = new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(lectures, (a, b) -> {
            if (a.start != b.start) {
                return a.start - b.start;
            }

            return a.end - b.end;
        });

        PriorityQueue<Lecture> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.end));

        for (Lecture lecture : lectures) {
            if (!pq.isEmpty() && lecture.start >= pq.peek().end) {
                pq.poll();
            }

            pq.add(lecture);
            count = Math.max(count, pq.size());
        }

        System.out.println(count);
        br.close();
    }

    static class Lecture {

        int start;
        int end;

        public Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }

    }

}
