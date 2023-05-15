import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        Arrays.fill(answer, 0);
        Map<String, Set<String>> userIdMap = new HashMap<>();
        Map<String, Set<String>> bandIdMap = new HashMap<>();
        for (String ids : report) {
            String[] split = ids.split(" ");
            if (userIdMap.containsKey(split[0])) {
                userIdMap.get(split[0]).add(split[1]);
            } else {
                Set<String> bidSet = new HashSet<>();
                bidSet.add(split[1]);
                userIdMap.put(split[0], bidSet);
            }
            if (bandIdMap.containsKey(split[1])) {
                bandIdMap.get(split[1]).add(split[0]);
            } else {
                Set<String> ridSet = new HashSet<>();
                ridSet.add(split[0]);
                bandIdMap.put(split[1], ridSet);
            }

        }
        for (int i = 0; i < id_list.length; i++) {
            if (userIdMap.containsKey(id_list[i])) {
                for (String bid : userIdMap.get(id_list[i])) {
                    if (k <= bandIdMap.get(bid).size()) {
                        answer[i] += 1;
                    }
                }
            }
        }
        return answer;
    }
}