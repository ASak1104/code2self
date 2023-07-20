import java.io.*;
import java.util.Scanner;

class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Scanner sc;

    static void readLine() throws IOException {
        sc = new Scanner(br.readLine());
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int tc = 1; tc <= 10; tc++) {
            readLine();

            int n = sc.nextInt();
            boolean calculable = true;

            while (n-- > 0) {
                readLine();

                if (calculable) {
                    sc.next();

                    boolean isNumber = sc.hasNextInt();

                    sc.next();
                    calculable = isNumber ^ sc.hasNext();
                }
            }

            bw.append(String.format("#%d %d\n", tc, calculable ? 1 : 0));
        }

        bw.flush();
        bw.close();
        br.close();
    }
}