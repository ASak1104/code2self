import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] liquors;
    static int n, a, b;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        liquors = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            liquors[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(liquors);

        a = 1_000_000_001;
        b = 1_000_000_001;

        for (int i = 0; i < n - 1; i++) {
            int left = liquors[i];
            int right = bisect(i + 1);

            if (Math.abs(a + b) > Math.abs(left + right)) {
                a = left;
                b = right;
            }
        }

        System.out.printf("%d %d", a, b);
    }

    private static int bisect(int left) {
        int aValue = liquors[left - 1];
        int ret = liquors[n - 1];
        int right = n - 1;

        while (left <= right) {
            int mid = (left + right) >>> 1;
            int bValue = liquors[mid];

            if (aValue + bValue == 0) {
                return bValue;
            }

            if (Math.abs(aValue + ret) > Math.abs(aValue + bValue)) {
                ret = bValue;
            }

            if (aValue + bValue < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ret;
    }

}
