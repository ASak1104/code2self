import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Point[] points = new Point[n + 1];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
        }
        br.close();

        Arrays.sort(points, 0, n, (a, b) -> {
            if (a.y != b.y) {
                return Long.compare(a.y, b.y);
            } else {
                return Long.compare(a.x, b.x);
            }
        });

        Arrays.sort(points, 1, n, (a, b) -> {
            int ccw = ccw(points[0], a, b);

            if (ccw != 0) {
                return -ccw;
            } else {
                return Long.compare(a.dist(points[0]), b.dist(points[0]));
            }
        });
        points[n] = points[0];

        Deque<Integer> stack = new ArrayDeque<>(n + 1);
        stack.add(0);
        stack.add(1);
        int next = 2;

        while (next < n + 1) {
            while (stack.size() > 1) {
                int second = stack.removeLast();
                int first = stack.getLast();

                if (ccw(points[first], points[second], points[next]) > 0) {
                    stack.addLast(second);
                    break;
                }
            }
            stack.addLast(next++);
        }
        
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(stack.size() - 1));
        bw.flush();
        bw.close();
    }

    public static int ccw(Point p0, Point p1, Point p2) {
        long res = 0L;
        res += p0.x * p1.y + p1.x * p2.y + p2.x * p0.y;
        res -= p0.y * p1.x + p1.y * p2.x + p2.y * p0.x;
        return Long.signum(res);
    }

    public static class Point {
        long x;
        long y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Long dist(Point o) {
            return (x - o.x) * (x - o.x) + (y - o.y) * (y - o.y);
        }

        @Override
        public String toString() {
            return String.format("%s %s", x, y);
        }
    }
}