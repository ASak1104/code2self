import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Point> pq = new PriorityQueue<>((p1, p2) -> p1.y != p2.y ? p2.y - p1.y : p2.x - p1.x);
        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;
            pq.add(new Point(d, p));
        }
        int answer = 0;
        int[] days = new int[10000];
        Arrays.fill(days, 0);
        while (!pq.isEmpty() ) {
            Point point = pq.poll();
            while (point.x >= 0 && days[point.x] == 1) {
                point.x -= 1;
            }
            if (point.x >= 0 && days[point.x] == 0) {
                days[point.x] = 1;
                answer += point.y;
            }
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}