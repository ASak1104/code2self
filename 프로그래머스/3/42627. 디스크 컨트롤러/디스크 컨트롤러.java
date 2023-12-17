import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {

    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }

            return a[1] - b[1];
        });

        int totalTime = 0;
        int currentTime = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));

        for (int[] job : jobs) {
            while (!pq.isEmpty() && currentTime < job[0]) {
                Node node = pq.poll();

                currentTime = Math.max(currentTime, node.start) + node.cost;
                totalTime += currentTime - node.start;
            }

            pq.add(new Node(job[0], job[1]));
        }

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            currentTime = Math.max(currentTime, node.start) + node.cost;
            totalTime += currentTime - node.start;
        }

        return totalTime / jobs.length;
    }

    static class Node {

        int start;
        int cost;

        Node(int start, int cost) {
            this.start = start;
            this.cost = cost;
        }
    }
}
