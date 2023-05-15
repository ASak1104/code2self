import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Main {

    public static int solution(int W, int[] costArray, int[] levelArray, List<List<Integer>> connectList) {
        int[] totalCostArray = new int[costArray.length];
        Queue<Point> queue = new LinkedList<>();
        for (int i = 1; i < levelArray.length; i++) {
            if (levelArray[i] == 0) {
                levelArray[i] -= 1;
                queue.add(new Point(i, costArray[i]));
                totalCostArray[i] = costArray[i];
            }
        }
        while (!queue.isEmpty()) {
            Point node = queue.poll();
            for (Integer nextNode : connectList.get(node.x)) {
                levelArray[nextNode] -= 1;
                totalCostArray[nextNode] = Math.max(totalCostArray[nextNode], node.y);
            }
            for (int i = 1; i < levelArray.length; i++) {
                if (levelArray[i] == 0) {
                    levelArray[i] -= 1;
                    totalCostArray[i] += costArray[i];
                    queue.add(new Point(i, totalCostArray[i]));
                }
            }
        }
        return totalCostArray[W];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());

            int[] costArray = new int[N + 1];
            int[] levelArray = new int[N + 1];
            List<List<Integer>> connectList = new ArrayList<>(N + 1);
            connectList.add(null);

            for (int i = 1; i <= N; i++) {
                costArray[i] = Integer.parseInt(st.nextToken());
                levelArray[i] = 0;
                connectList.add(new ArrayList<>());
            }

            while (K-- > 0) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                levelArray[Y] += 1;
                connectList.get(X).add(Y);
            }
            int W = Integer.parseInt(br.readLine());
            answer.append(solution(W, costArray, levelArray, connectList)).append('\n');
        }
        System.out.println(answer);
    }
}