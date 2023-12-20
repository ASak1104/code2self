import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {

    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Point> pq = new PriorityQueue<>(k, Comparator.reverseOrder());

        Arrays.stream(points)
                .map(Point::new)
                .forEach(point -> {
                    if (pq.size() == k && point.compareTo(pq.peek()) < 0) {
                        pq.poll();
                    }

                    if (pq.size() < k) {
                        pq.offer(point);
                    }
                });

        return pq.stream()
                .map(point -> point.point)
                .toArray(int[][]::new);
    }

    static class Point implements Comparable<Point> {

        int[] point;
        double dist;

        Point(int[] point) {
            this.point = point;
            this.dist = Math.sqrt(point[0] * point[0] + point[1] * point[1]);
        }

        @Override
        public int compareTo(Point o) {
            return Double.compare(dist, o.dist);
        }

    }

}
