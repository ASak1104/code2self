import java.io.*;
import java.util.ArrayList;

import static java.util.Comparator.comparingInt;

public class Main {
    static int[] buildTree(int n) {
        int capacity = 1;
        while (capacity < n)
            capacity *= 2;
        int[] ret = new int[capacity << 1];
        ret[0] = capacity;
        return ret;
    }

    static void update(int[] tree, int y) {
        int node = tree[0] + y;
        while (node > 0) {
            tree[node]++;
            node >>= 1;
        }
    }

    static int query(int[] tree, int qs, int qe) {
        int start = tree[0] + qs;
        int end = tree[0] + qe;
        int ret = 0;
        while (start < end) {
            if ((start & 1) == 1)
                ret += tree[start++];
            if ((end & 1) == 0)
                ret += tree[end--];
            start >>= 1;
            end >>= 1;
        }
        if (start == end)
            ret += tree[start];
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());

        while (testCase-- > 0) {
            int n = Integer.parseInt(br.readLine());
            ArrayList<int[]> islands = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                String[] island = br.readLine().split(" ");
                islands.add(new int[] { Integer.parseInt(island[0]), Integer.parseInt(island[1]) });
            }

            islands.sort(comparingInt((int[] a) -> a[1]).reversed());

            int count = 0;
            for (int i = n - 1; i > 0; i--) {
                if (islands.get(i)[1] == islands.get(i - 1)[1]) {
                    islands.get(i)[1] = count;
                } else {
                    islands.get(i)[1] = count++;
                }
            }
            islands.get(0)[1] = count;
            islands.sort(comparingInt((int[] a) -> a[0]));

            int[] tree = buildTree(count + 1);
            long answer = 0L;
            for (int[] island : islands) {
                answer += query(tree, island[1], count);
                update(tree, island[1]);
            }

            bw.write(Long.toString(answer));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}