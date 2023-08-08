//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
//class Main {
//    static StringTokenizer st;
//    static int[][] matrices, dp;
//    static int n;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        n = Integer.parseInt(br.readLine());
//
//        matrices = new int[n][2];
//
//        for (int i = 0; i < n; i++) {
//            st = new StringTokenizer(br.readLine());
//            matrices[i][0] = nextInt();
//            matrices[i][1] = nextInt();
//        }
//
//        dp = new int[n][n];
//
//        for (int k = 1; k < n; k++) {
//            for (int i = 0; i < n - k; i++) {
//                int leftLast = matrices[i][0] * matrices[i][1] * matrices[i + k][1] + dp[k - 1][i + 1];
//                int leftFirst = matrices[i][0] * matrices[i + k][0] * matrices[i + k][1] + dp[k - 1][i];
//
//                dp[k][i] = Math.min(leftLast, leftFirst);
//            }
//        }
//
//        System.out.println(dp[n - 1][0]);
//        br.close();
//    }
//
//    static int nextInt() {
//        return Integer.parseInt(st.nextToken());
//    }
//}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

class Main {
    static int[][] dp;
    static String s1, s2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s1 = br.readLine();
        s2 = br.readLine();

        dp = new int[s1.length() + 1][s2.length() + 1];

        for (int r = 1; r <= s1.length(); r++) {
            for (int c = 1; c <= s2.length(); c++) {
                if (s1.charAt(r - 1) == s2.charAt(c - 1)) {
                    dp[r][c] = dp[r - 1][c - 1] + 1;
                } else {
                    dp[r][c] = Math.max(dp[r][c - 1], dp[r - 1][c]);
                }
            }
        }

        int r = s1.length();
        int c = s2.length();
        Deque<Character> stack = new ArrayDeque<>(dp[r][c]);

        while (dp[r][c] > 0) {
            if (dp[r][c] == dp[r - 1][c]) {
                r--;
            } else if (dp[r][c] == dp[r][c - 1]) {
                c--;
            } else {
                stack.addLast(s1.charAt(--r));
                c--;
            }
        }

        StringBuilder sb = new StringBuilder();

        sb.append(stack.size()).append('\n');

        while (!stack.isEmpty()) {
            sb.append(stack.removeLast());
        }

        System.out.println(sb);
        br.close();
    }
}