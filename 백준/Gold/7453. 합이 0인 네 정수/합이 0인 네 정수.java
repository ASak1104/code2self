import java.io.*;
import java.util.*;

public class Main {

    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));

    static int readInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException {
        int n = readInt();
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];
        int[] arr3 = new int[n];
        int[] arr4 = new int[n];

        for (int i = 0; i < n; i++) {
            arr1[i] = readInt();
            arr2[i] = readInt();
            arr3[i] = readInt();
            arr4[i] = readInt();
        }

        int nn = n * n;
        int[] sums1 = new int[nn];
        int[] sums2 = new int[nn];

        int i = 0;

        for (int v1 : arr1) {
            for (int v2 : arr2) {
                sums1[i++] = v1 + v2;
            }
        }

        int j = 0;

        for (int v1 : arr3) {
            for (int v2 : arr4) {
                sums2[j++] = v1 + v2;
            }
        }

        Arrays.sort(sums1);
        Arrays.sort(sums2);

        long res = 0;
        i = 0;
        j = sums2.length - 1;

        while (i < nn && j >= 0) {
            while (i < nn && sums1[i] + sums2[j] < 0) i++;
            while (i < nn && j >= 0 && sums1[i] + sums2[j] > 0) j--;

            if (i >= nn || j < 0) break;

            if (sums1[i] + sums2[j] == 0) {
                int t1 = sums1[i];
                int t2 = sums2[j];
                int cnt1 = 0;
                int cnt2 = 0;

                while (i < nn && sums1[i] == t1) {
                    cnt1++;
                    i++;
                }

                while (j >= 0 && sums2[j] == t2) {
                    cnt2++;
                    j--;
                }

                res += (long) cnt1 * cnt2;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.append(Long.toString(res));
        bw.flush();
        bw.close();
    }
}