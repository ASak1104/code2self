import java.io.*;
import java.util.StringTokenizer;

class Main {
    static final int MAX = (int) 1e9;
    static int T, K, C;
    static long s, t, r;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            EEA(K, C);

            if (r != 1) {
                bw.append("IMPOSSIBLE\n");
                continue;
            }

            while (s >= 0 || t <= 0) {
                s -= C;
                t += K;
            }

            if (t > MAX) {
                bw.append("IMPOSSIBLE\n");
                continue;
            }

            bw.append(String.valueOf(t));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int gcd(int a, int b) {
        while (b > 0) {
            int r = a % b;

            a = b;
            b = r;
        }

        return a;
    }

    static void EEA(long a, long b) {
        long s0 = 1, t0 = 0, r0 = a;
        long s1 = 0, t1 = 1, r1 = b;
        long q, temp;

        while (r1 > 0) {
            q = r0 / r1;

            temp = s0 - s1 * q;
            s0 = s1;
            s1 = temp;

            temp = t0 - t1 * q;
            t0 = t1;
            t1 = temp;

            temp = r0 - r1 * q;
            r0 = r1;
            r1 = temp;
        }

        s = s0;
        t = t0;
        r = r0;
    }
}