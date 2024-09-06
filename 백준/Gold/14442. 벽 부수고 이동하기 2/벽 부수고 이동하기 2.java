import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    private static final int INF = (int) 1e9;
    private static final int[][] stepWeights = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static boolean[][][] visit;
    private static boolean[][] map;
    private static int N, M, K, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];
        visit = new boolean[N][M][K + 1];
        answer = INF;

        for (int r = 0; r < N; r++) {
            char[] row = br.readLine().toCharArray();

            for (int c = 0; c < M; c++) {
                map[r][c] = row[c] == '0';
            }
        }

        simulate();

        System.out.println(answer != INF ? answer : -1);
        br.close();
    }

    private static void simulate() {
        Deque<Step> dq = new ArrayDeque<>();

        dq.addLast(new Step(0, 0, 0, 1));
        visit[0][0][0] = true;

        while (!dq.isEmpty()) {
            Step u = dq.removeFirst();

            if (u.r == N - 1 && u.c == M - 1) {
                answer = Math.min(answer, u.step);

                continue;
            }

            for (int[] w : stepWeights) {
                int vr = u.r + w[0];
                int vc = u.c + w[1];

                if (vr < 0 || vr >= N || vc < 0 || vc >= M) {
                    continue;
                }

                if (map[vr][vc] && !visit[vr][vc][u.k]) {
                    dq.addLast(new Step(vr, vc, u.k, u.step + 1));
                    visit[vr][vc][u.k] = true;
                }

                if (!map[vr][vc] && u.k < K && !visit[vr][vc][u.k + 1]) {
                    dq.addLast(new Step(vr, vc, u.k + 1, u.step + 1));
                    visit[vr][vc][u.k + 1] = true;
                }
            }
        }
    }

}

class Step {

    int r;
    int c;
    int k;
    int step;

    public Step(int r, int c, int k, int step) {
        this.r = r;
        this.c = c;
        this.k = k;
        this.step = step;
    }

}
