import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String[] befores, afters;
    static Set<String> visits;
    static int n, count = 0;

    public static void main(String[] args) throws IOException {
        init();

        int bi = 0;
        int ai = 0;

        while (bi < n) {
            if (visits.contains(befores[bi])) {
                bi++;
                continue;
            }

            while (ai < n && !befores[bi].equals(afters[ai])) {
                visits.add(afters[ai]);
                count++;
                ai++;
            }

            bi++;
            ai++;
        }

        System.out.println(count);
        br.close();
    }

    public static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        befores = new String[n];
        afters = new String[n];
        visits = new HashSet<>(n);

        for (int i = 0; i < n; i++) {
            befores[i] = br.readLine();
        }

        for (int i = 0; i < n; i++) {
            afters[i] = br.readLine();
        }
    }

}
