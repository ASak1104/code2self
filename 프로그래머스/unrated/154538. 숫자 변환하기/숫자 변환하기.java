import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        heap.add(new Node(0, y));
        while (!heap.isEmpty()) {
            Node node = heap.poll();
            if (node.value == x) {
                return node.dist;
            }
            if (node.value - n >= x) {
                heap.add(new Node(node.dist + 1, node.value - n));
            }
            if (node.value % 3 == 0 && node.value / 3 >= x) {
                heap.add(new Node(node.dist + 1, node.value / 3));
            }
            if (node.value % 2 == 0 && node.value >> 1 >= x) {
                heap.add(new Node(node.dist + 1, node.value >> 1));
            }
        }
        return -1;
    }

    private static class Node {
        int dist;
        int value;

        public Node(int dist, int value) {
            this.dist = dist;
            this.value = value;
        }
    }
}