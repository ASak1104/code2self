import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Map<String, Integer> counter = new HashMap<>();
        TreeSet<String> names = new TreeSet<>();
        String name = br.readLine();
        int total = 0;

        while (name != null) {
            names.add(name);
            counter.put(name, counter.getOrDefault(name, 0) + 1);
            total++;

            name = br.readLine();
        }

        for (String s : names) {
            double per = 100.0 * counter.get(s) / total;

            bw.append(s);
            bw.append(String.format(" %.4f\n", per));
        }

        bw.flush();
        bw.close();
    }
}