import java.io.*;
import java.util.*;

public class Main {

    static StreamTokenizer sttk = new StreamTokenizer(new InputStreamReader(System.in));

    static int readInt() throws IOException {
        sttk.nextToken();
        return (int) sttk.nval;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int u = readInt();
        int v = readInt();
        int tc = 1;

        while (u >= 0 && v >= 0) {
            boolean isTree = true;
            Set<Integer> nodes = new HashSet<>();
            Set<Integer> subNodes = new HashSet<>();

            while (u > 0 && v > 0) {
                nodes.add(u);
                nodes.add(v);

                if (subNodes.contains(v)) {
                    isTree = false;
                } else {
                    subNodes.add(v);
                }

                u = readInt();
                v = readInt();
            }

            if (isTree && nodes.size() > 0) {
                int rootCount = 0;

                for (int vertex : nodes) {
                    if (!subNodes.contains(vertex)) {
                        rootCount++;
                    }
                }

                isTree = rootCount == 1;
            }

            if (isTree) {
                bw.append(String.format("Case %d is a tree.\n", tc++));
            } else {
                bw.append(String.format("Case %d is not a tree.\n", tc++));
            }

            u = readInt();
            v = readInt();
        }

        bw.flush();
        bw.close();
    }
}