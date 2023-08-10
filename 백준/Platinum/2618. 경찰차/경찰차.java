import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static StringBuilder sb;
    static int[][] events, memo;
    static int n, w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        w = Integer.parseInt(br.readLine());

        events = new int[w + 2][2];
        events[0][0] = events[0][1] = 1;
        events[1][0] = events[1][1] = n;

        for (int i = 2; i < w + 2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            events[i][0] = Integer.parseInt(st.nextToken());
            events[i][1] = Integer.parseInt(st.nextToken());
        }

        memo = new int[w + 2][w + 2];

        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        sb = new StringBuilder();

        sb.append(travel(2, 0, 1)).append('\n');

        trace(2, 0, 1);

        System.out.println(sb);
        br.close();
    }

    static int travel(int event, int car1, int car2) {
        if (event >= w + 2) {
            return memo[car1][car2] = 0;
        }

        if (memo[car1][car2] != -1) {
            return memo[car1][car2];
        }

        int ret = Integer.MAX_VALUE;

        ret = Math.min(ret, travel(event + 1, event, car2) + dist(car1, event));
        ret = Math.min(ret, travel(event + 1, car1, event) + dist(car2, event));

        return memo[car1][car2] = ret;
    }

    static int dist(int e1, int e2) {
        int[] p1 = events[e1];
        int[] p2 = events[e2];

        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }

    static void trace(int event, int car1, int car2) {
        if (event >= w + 2) return;

        if (memo[car1][car2] == memo[event][car2] + dist(car1, event)) {
            sb.append(1).append('\n');
            trace(event + 1, event, car2);
        } else {
            sb.append(2).append('\n');
            trace(event + 1, car1, event);
        }
    }
}