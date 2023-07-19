import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Solution {

    private static final StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));

    private static int readInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    private static String readString() throws IOException {
        st.nextToken();
        return st.sval;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = 0;

        while (++testCase < 11) {
            int n = readInt();
            List<Integer> list = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                list.add(readInt());
            }

            int m = readInt();

            while (m-- > 0) {
                String cmd = readString();
                int x;
                int y;

                switch (cmd) {
                    case "I":
                        x = readInt();
                        y = readInt();

                        for (int i = 0; i < y; i++) {
                            list.add(x + i, readInt());
                        }

                        break;
                    case "D":
                        x = readInt();
                        y = readInt();

                        while (y-- > 0 && list.size() > x) {
                            list.remove(x);
                        }

                        break;
                    default:
                        y = readInt();

                        while (y-- > 0) {
                            list.add(readInt());
                        }

                        break;
                }
            }

            bw.append(String.format("#%d ", testCase));

            int end = Math.min(10, list.size());

            for (int i = 0; i < end; i++) {
                bw.append(String.format("%d ", list.get(i)));
            }

            bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}