import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

class Main {

    static StreamTokenizer sttk = new StreamTokenizer(new InputStreamReader(System.in));

    static int readInt() throws IOException {
        sttk.nextToken();
        return (int) sttk.nval;
    }

    public static void main(String[] args) throws IOException {
        int n = readInt();
        int[] seq = new int[n];
        int[] LR = new int[n];
        int[] RL = new int[n];

        for (int i = 0; i < n; i++) {
            seq[i] = readInt();
            LR[i] = seq[i];
            RL[i] = seq[i];
        }

        for (int i = 1; i < n; i++) {
            LR[i] = gcd(LR[i], LR[i - 1]);
        }

        for (int i = n - 2; i >= 0; i--) {
            RL[i] = gcd(RL[i], RL[i + 1]);
        }

        int res = -1;
        int num = -1;

        for (int i = 0; i < n; i++) {
            int gcd;

            if (i == 0) {
                gcd = RL[i + 1];
            } else if (i == n - 1) {
                gcd = LR[i - 1];
            } else {
                gcd = gcd(LR[i - 1], RL[i + 1]);
            }

            if (seq[i] % gcd != 0 && gcd > res) {
                res = gcd;
                num = seq[i];
            }
        }

        if (res == -1) {
            System.out.println(-1);
            return;
        }

        System.out.printf("%d %d", res, num);
    }

    static int gcd(int a, int b) {
        if (a < b) return gcd(b, a);

        while (b > 0) {
            int temp = a;

            a = b;
            b = temp % b;
        }

        return a;
    }
}