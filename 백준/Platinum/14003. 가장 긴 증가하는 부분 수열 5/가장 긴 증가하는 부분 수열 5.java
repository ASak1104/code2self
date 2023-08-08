import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static StringTokenizer st;
    static int[] seq, dp;
    static List<Integer> lis;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        seq = new int[n];

        for (int i = 0; i < n; i++) {
            seq[i] = nextInt();
        }

        dp = new int[n];
        lis = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            int v = seq[i];

            if (lis.isEmpty() || v > lis.get(lis.size() - 1)) {
                lis.add(v);
                dp[i] = lis.size();
            } else {
                int idx = bisect(v);

                dp[i] = idx + 1;

                if (lis.get(idx) > v) {
                    lis.set(idx, v);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        int size = lis.size();
        int i = n;
        Deque<Integer> stack = new ArrayDeque<>(size);

        while (size > 0 && --i >= 0) {
            if (dp[i] == size) {
                stack.addLast(seq[i]);
                size--;
            }
        }

        sb.append(lis.size()).append('\n');

        while (!stack.isEmpty()) {
            sb.append(stack.removeLast()).append(' ');
        }

        System.out.println(sb);
        br.close();
    }

    static int bisect(int v) {
        int s = 0;
        int e = lis.size() - 1;
        int ret = 0;

        while (s <= e) {
            int mid = (s + e) >>> 1;
            int mv = lis.get(mid);

            if (mv == v) return mid;

            if (mv < v) {
                s = mid + 1;
            } else {
                ret = mid;
                e = mid - 1;
            }
        }

        return ret;
    }

    static int nextInt() {
        return Integer.parseInt(st.nextToken());
    }
}