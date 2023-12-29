import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {

    static String START = "ICN";

    Map<String, PriorityQueue<String>> edges = new HashMap<>(10_000);
    List<String> answer = new ArrayList<>(10_000);

    public String[] solution(String[][] tickets) {
        for (String[] ticket : tickets) {
            String u = ticket[0];
            String v = ticket[1];

            edges.computeIfAbsent(u, value -> new PriorityQueue<>()).add(v);
            edges.computeIfAbsent(v, value -> new PriorityQueue<>());
        }

        travel(START);

        Collections.reverse(answer);

        return answer.toArray(String[]::new);
    }

    void travel(String u) {
        PriorityQueue<String> edge = edges.get(u);

        while (!edge.isEmpty()) {
            travel(edge.poll());
        }

        answer.add(u);
    }

}
