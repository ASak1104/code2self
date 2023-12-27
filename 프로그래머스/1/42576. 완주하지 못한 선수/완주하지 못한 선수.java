import java.util.HashMap;
import java.util.Map;

class Solution {

    static Map<String, Integer> counts = new HashMap<>(100_000);

    public String solution(String[] participant, String[] completion) {
        for (String name : completion) {
            counts.put(name, counts.getOrDefault(name, 0) + 1);
        }

        for (String name : participant) {
            if (counts.getOrDefault(name, 0) == 0) {
                return name;
            }

            counts.put(name, counts.get(name) - 1);
        }

        return null;
    }

}
