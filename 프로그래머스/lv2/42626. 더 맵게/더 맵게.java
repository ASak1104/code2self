import java.util.PriorityQueue;

class Solution {
    PriorityQueue<Integer> pq;
    int count;
    
    public int solution(int[] scoville, int K) {
        pq = new PriorityQueue(scoville.length);
        
        for (int v : scoville) {
            pq.add(v);
        }
        
        count = 0;
        
        while (!pq.isEmpty()) {
            int first = pq.poll();
            
            if (first >= K) return count;
            
            if (pq.isEmpty()) return -1;
            
            int second = pq.poll();
            
            pq.add(first + second * 2);
            count++;
        }
        
        return -1;
    }
}