import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String[] strings;
    static char[][] chars;
    static int rows, cols;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        rows = Integer.parseInt(st.nextToken());
        cols = Integer.parseInt(st.nextToken());

        chars = new char[rows][cols];
        strings = new String[cols];

        for (int r = 0; r < rows; r++) {
            chars[r] = br.readLine().toCharArray();
        }

        for (int c = 0; c < cols; c++) {
            StringBuilder sb = new StringBuilder(rows);

            for (int r = 0; r < rows; r++) {
                sb.append(chars[r][c]);
            }

            strings[c] = sb.toString();
        }

        System.out.println(findBound());
        br.close();
    }

    private static int findBound() {
        int left = 1;
        int right = rows - 1;
        int bound = 0;

        while (left <= right) {
            int mid = (left + right) >>> 1;
            boolean isDuplicate = isDuplicate(mid);

            if (isDuplicate) {
                right = mid - 1;
            } else {
                left = mid + 1;
                bound = mid;
            }
        }

        return bound;
    }

    private static boolean isDuplicate(int start) {
        Set<String> words = new HashSet<>();

        for (int c = 0; c < cols; c++) {
            String word = strings[c].substring(start);

            if (words.contains(word)) {
                return true;
            }

            words.add(word);
        }

        return false;
    }

}
