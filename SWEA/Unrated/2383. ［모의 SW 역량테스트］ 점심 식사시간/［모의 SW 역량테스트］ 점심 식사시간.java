import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();

    private static List<int[]> people, stairs;
    private static int answer;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());

            people = new ArrayList<>();
            stairs = new ArrayList<>();

            for (int r = 0; r < N; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                for (int c = 0; c < N; c++) {
                    int value = Integer.parseInt(st.nextToken());

                    if (value == 1) {
                        people.add(new int[]{r, c});
                    } else if (value > 1) {
                        stairs.add(new int[]{r, c, value});
                    }
                }
            }

            answer = Integer.MAX_VALUE;

            solve(0, new int[people.size()]);

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        br.close();

        System.out.println(sb);
    }

    private static void solve(int i, int[] cases) {
        if (i == cases.length) {
            answer = Math.min(answer, simulate(cases));
            return;
        }

        cases[i] = 0;
        solve(i + 1, cases);

        cases[i] = 1;
        solve(i + 1, cases);
    }

    private static int simulate(int[] cases) {
        PriorityQueue<Integer> first = new PriorityQueue<>();
        PriorityQueue<Integer> second = new PriorityQueue<>();

        for (int i = 0; i < cases.length; i++) {
            int[] person = people.get(i);
            int[] stair = stairs.get(cases[i]);
            PriorityQueue<Integer> target = cases[i] == 0 ? first : second;

            target.add(Math.abs(person[0] - stair[0]) + Math.abs(person[1] - stair[1]));
        }

        return Math.max(calc(first, stairs.get(0)[2]), calc(second, stairs.get(1)[2]));
    }

    private static int calc(PriorityQueue<Integer> waitingPeople, int cost) {
        PriorityQueue<Integer> usingPeople = new PriorityQueue<>(3);
        int time = 0;

        while (!waitingPeople.isEmpty()) {
            if (usingPeople.size() == 3) {
                time = usingPeople.poll();
            }

            usingPeople.add(Math.max(time, waitingPeople.poll()) + cost);
        }

        while (!usingPeople.isEmpty()) {
            time = usingPeople.poll();
        }

        return time + 1;
    }

}
