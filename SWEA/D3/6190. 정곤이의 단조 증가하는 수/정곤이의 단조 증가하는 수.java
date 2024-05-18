import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder sb = new StringBuilder();

    static StringTokenizer st;
    static int[] nums;
    static int T, N;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            nums = new int[N];

            for (int i = 0; i < N; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            sb.append(String.format("#%d %d", tc, findMaxIncSeq()))
                .append('\n');
        }

        System.out.println(sb);
    }

    static int findMaxIncSeq() {
        int ret = -1;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int target = nums[i] * nums[j];

                if (isIncSeq(target)) {
                    ret = Math.max(ret, target);
                }
            }
        }

        return ret;
    }

    static boolean isIncSeq(int target) {
        int prev = 9;

        while (target > 0) {
            int p = target / 10;
            int q = target % 10;

            if (q > prev) {
                return false;
            }

            target = p;
            prev = q;
        }

        return true;
    }

}
