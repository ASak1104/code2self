import java.util.ArrayDeque;
import java.util.Deque;

class Solution {

    static int[] rowWeights = new int[]{0, 1, 0, -1};
    static int[] colWeights = new int[]{1, 0, -1, 0};

    int[][] maps;
    boolean[][] visits;
    int n, m;

    public int solution(int[][] maps) {
        this.n = maps.length;
        this.m = maps[0].length;
        this.maps = maps;

        return travel();
    }

    private int travel() {
        visits = new boolean[n][m];
        Deque<int[]> deque = new ArrayDeque<>();

        visits[0][0] = true;
        deque.addLast(new int[]{0, 0, 1});

        while (!deque.isEmpty()) {
            int r = deque.getFirst()[0];
            int c = deque.getFirst()[1];
            int w = deque.removeFirst()[2];

            for (int i = 0; i < 4; i++) {
                int vr = r + rowWeights[i];
                int vc = c + colWeights[i];

                if (!isValid(vr, vc)) {
                    continue;
                }

                if (vr == n - 1 && vc == m - 1) {
                    return w + 1;
                }

                visits[vr][vc] = true;
                deque.addLast(new int[]{vr, vc, w + 1});
            }
        }

        return -1;
    }

    private boolean isValid(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m && maps[r][c] == 1 && !visits[r][c];
    }

}
