import java.io.*;
import java.util.Scanner;

class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int tc = 1; tc <= 10; tc++) {
            int n = Integer.parseInt(br.readLine());
            boolean calculable = true;

            while (n-- > 0) {
                if (!calculable) {
                    br.readLine();
                    continue;
                }

                Scanner sc = new Scanner(br.readLine());

                sc.next();

                boolean isNumber = sc.hasNextInt();

                sc.next();
                
                calculable = isNumber ^ sc.hasNext();
                
                sc.close();
            }

            bw.append(String.format("#%d %d\n", tc, calculable ? 1 : 0));
        }

        bw.flush();
        bw.close();
        br.close();
    }
}