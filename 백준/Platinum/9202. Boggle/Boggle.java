import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {

    static Trie trie = new Trie();
    static char[][] board = new char[4][4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        while (n-- > 0) {
            trie.addWord(br.readLine());
        }

        br.readLine();

        int m = Integer.parseInt(br.readLine());

        while (m-- > 0) {
            for (int r = 0; r < 4; r++) {
                String row = br.readLine();

                for (int c = 0; c < 4; c++) {
                    board[r][c] = row.charAt(c);
                }
            }

            trie.travel(board);

            int point = trie.point;
            String longest = trie.longest;
            int count = trie.count;

            bw.append(String.format("%d %s %d\n", point, longest, count));
            br.readLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

class Trie {
    Node root = new Node();
    int[] rowW = {-1, -1, -1, 0, 0, 1, 1, 1};
    int[] colW = {-1, 0, 1, -1, 1, -1, 0, 1};
    List<Node> visit = new ArrayList<>();
    int point = 0;
    String longest = "";
    int count = 0;


    void addWord(String s) {
        Node node = root;

        for (int i = 0; i < s.length(); i++) {
            node.add(s.charAt(i));
            node = node.get(s.charAt(i));
        }

        node.isWord = true;
        node.s = s;
    }

    void travel(char[][] board) {
        count = 0;
        point = 0;
        longest = "";
        visit.clear();

        Deque<Point> queue = new ArrayDeque<>(16);

        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                if (root.has(board[r][c])) {
                    queue.addLast(new Point(r, c, 1, root.get(board[r][c])));
                }
            }
        }

        while (!queue.isEmpty()) {
            Point u = queue.removeFirst();
            Node node = u.node;

            if (node.isWord && !node.visit) {
                switch (u.dist) {
                    case 8:
                        point += 6;
                    case 7:
                        point += 2;
                    case 6:
                        point++;
                    case 5:
                        point++;
                    case 4:
                    case 3:
                        point++;
                }

                node.visit = true;
                count++;
                visit.add(node);
            }

            if (u.dist == 8) continue;

            for (int i = 0; i < rowW.length; i++) {
                int vr = u.r + rowW[i];
                int vc = u.c + colW[i];

                if (vr < 0 || vr > 3 || vc < 0 || vc > 3) continue;

                if (!node.has(board[vr][vc])) continue;

                if (u.visit[vr * 4 + vc]) continue;;

                Point next = new Point(vr, vc, u.dist + 1, node.get(board[vr][vc]));

                next.setVisit(u);
                queue.addLast(next);
            }
        }

        for (Node node : visit) {
            String s = node.s;

            if (s.length() > longest.length()) {
                longest = s;
            } else if (s.length() == longest.length() && s.compareTo(longest) < 0) {
                longest = s;
            }

            node.visit = false;
        }
    }
}

class Node {
    Node[] children = new Node[26];
    Node prev;
    boolean isWord = false;
    String s;
    boolean visit = false;

    void add(char c) {
        if (!has(c)) {
            children[c - 'A'] = new Node();
            children[c - 'A'].prev = this;
        }
    }

    Node get(char c) {
        return children[c - 'A'];
    }

    boolean has(char c) {
        return children[c - 'A'] != null;
    }
}

class Point {
    int r;
    int c;
    int dist;
    Node node;
    boolean[] visit = new boolean[16];

    Point(int r, int c, int dist, Node node) {
        this.r = r;
        this.c = c;
        this.dist = dist;
        this.node = node;

        visit[r * 4 + c] = true;
    }

    void setVisit(Point o) {
        for (int i = 0; i < 16; i++) {
            if (o.visit[i]) {
                visit[i] = true;
            }
        }
    }
}