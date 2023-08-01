import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static StringBuilder sb = new StringBuilder();
    static boolean[] visit = new boolean[21];
    static int[] seq = new int[20];
    static long[] fact = new long[20];
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());

        fact[0] = 1;

        for (int i = 1; i < n; i++) {
            fact[i] = fact[i - 1] * i;
        }

        if (k == 1) {
            long order = Long.parseLong(st.nextToken());

            findSeq(order);
        } else {
            for (int i = 0; i < n; i++) {
                seq[i] = Integer.parseInt(st.nextToken());
            }

            findOrder();
        }

        System.out.println(sb);
        br.close();
    }

    static void findOrder() {
        long order = 1;
        int count = n - 1;

        for (int num : seq) {
            for (int i = 1; i <= n; i++) {
                if (visit[i]) continue;

                if (num == i) {
                    visit[i] = true;
                    break;
                }

                order += fact[count];
            }

            count--;
        }

        sb.append(order);
    }

    static void findSeq(long order) {
        int count = n - 1;

        while (count > 0) {
            for (int i = 1; i <= n; i++) {
                if (visit[i]) continue;

                if (order <= fact[count]) {
                    visit[i] = true;
                    sb.append(i);
                    sb.append(' ');
                    break;
                }

                order -= fact[count];
            }

            count--;
        }

        for (int i = 1; i <= n; i++) {
            if (!visit[i]) {
                sb.append(i);
                return;
            }
        }
    }
}