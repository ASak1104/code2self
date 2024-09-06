import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    private static final Pair[] weights = {
        new Pair(0, 1),
        new Pair(1, 0),
        new Pair(-1, 0),
        new Pair(0, -1),
    };

    private static List<Pair> targets;
    private static boolean[][] cores, cells;
    private static int T, N, maxCore, maxCable;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            cores = new boolean[N][N];
            cells = new boolean[N][N];
            targets = new ArrayList<>();
            maxCore = 0;
            maxCable = 0;

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());

                for (int c = 0; c < N; c++) {
                    cores[r][c] = Integer.parseInt(st.nextToken()) == 1;
                    cells[r][c] = !cores[r][c];
                }
            }

            for (int r = 1; r < N - 1; r++) {
                for (int c = 1; c < N - 1; c++) {
                    if (cores[r][c]) {
                        targets.add(new Pair(r, c));
                    }
                }
            }

            simulate(0, 0, 0);

            sb.append('#').append(t).append(' ').append(maxCable).append('\n');
        }

        System.out.println(sb);
        br.close();
    }

    private static void simulate(int i, int core, int cable) {
        if (i == targets.size()) {
            if (core > maxCore || (core == maxCore && cable < maxCable)) {
                maxCore = core;
                maxCable = cable;
            }

            return;
        }

        Pair coil = targets.get(i);

        for (Pair w : weights) {
            int count = mark(coil, w);

            if (count > 0) {
                simulate(i + 1, core + 1, cable + count);
                unmark(coil, w);
            } else {
                simulate(i + 1, core, cable);
            }
        }
    }

    private static int mark(Pair core, Pair w) {
        int count = 0;
        int r = core.r + w.r;
        int c = core.c + w.c;

        while (0 <= r && r < N && 0 <= c && c < N) {
            if (cores[r][c] || !cells[r][c]) {
                while (true) {
                    r -= w.r;
                    c -= w.c;

                    if (r == core.r && c == core.c) {
                        break;
                    }

                    cells[r][c] = true;
                }

                return 0;
            }

            cells[r][c] = false;
            count++;
            r += w.r;
            c += w.c;
        }

        return count;
    }

    private static void unmark(Pair core, Pair w) {
        int r = core.r + w.r;
        int c = core.c + w.c;

        while (0 <= r && r < N && 0 <= c && c < N) {
            cells[r][c] = true;
            r += w.r;
            c += w.c;
        }
    }

}

class Pair {

    int r;
    int c;

    public Pair(int r, int c) {
        this.r = r;
        this.c = c;
    }

}
