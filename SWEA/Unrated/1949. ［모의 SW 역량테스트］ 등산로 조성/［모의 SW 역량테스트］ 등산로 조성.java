import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    private static final int[][] weights = new int[][]{
        new int[]{0, 1},
        new int[]{1, 0},
        new int[]{0, -1},
        new int[]{-1, 0},
    };

    private static int[][] map;
    private static int T, N, K, target, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            target = 0;
            answer = 0;

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());

                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                    target = Math.max(target, map[r][c]);
                }
            }

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    simulate(r, c, 1, true);

                    map[r][c] -= K;
                    simulate(r, c, 1, false);
                    map[r][c] += K;
                }
            }

            sb.append('#').append(t).append(' ').append(answer).append('\n');
        }

        System.out.println(sb);
        br.close();
    }

    private static void simulate(int ur, int uc, int hob, boolean cutable) {
        if (map[ur][uc] == target) {
            answer = Math.max(answer, hob);
        }

        for (int[] weight : weights) {
            int vr = ur + weight[0];
            int vc = uc + weight[1];

            if (vr < 0 || vr >= N || vc < 0 || vc >= N || map[ur][uc] >= map[vr][vc]) {
                continue;
            }

            simulate(vr, vc, hob + 1, cutable);

            if (cutable) {
                int origin = map[vr][vc];

                map[vr][vc] = Math.max(map[ur][uc] + 1, map[vr][vc] - K);
                simulate(vr, vc, hob + 1, false);
                map[vr][vc] = origin;
            }
        }
    }

}
