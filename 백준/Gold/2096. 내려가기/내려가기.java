import java.io.*;

public class Main {

    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));

    static int readInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException {
        int n = readInt();
        int[] minCum = new int[3];
        int[] maxCum = new int[3];
        int[] minWin = {readInt(), readInt(), readInt()};
        int[] maxWin = minWin.clone();

        for (int i = 1; i < n; i++) {
            for (int c = 0; c < 3; c++) {
                minCum[c] = minWin[c];
                maxCum[c] = maxWin[c];

                minWin[c] = readInt();
                maxWin[c] = minWin[c];
            }

            for (int c = 0; c < 3; c++) {
                minWin[c] += minWith(minCum, c);
                maxWin[c] += maxWith(maxCum, c);
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.append(String.format("%d %d", maxWith(maxWin, 1), minWith(minWin, 1)));
        bw.flush();
        bw.close();
    }

    static int minWith(int[] arr, int c) {
        if (c == 1) {
            return Math.min(arr[0], Math.min(arr[1], arr[2]));
        }

        return Math.min(arr[c], arr[1]);
    }

    static int maxWith(int[] arr, int c) {
        if (c == 1) {
            return Math.max(arr[0], Math.max(arr[1], arr[2]));
        }

        return Math.max(arr[c], arr[1]);
    }
}