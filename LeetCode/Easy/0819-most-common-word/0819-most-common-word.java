import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class Solution {

    private static final String SPECIAL = "[!?',;.\\s]";

    private final Set<String> bannedWords = new HashSet<>();
    private final Map<String, Integer> wordsCounts = new HashMap<>();

    public String mostCommonWord(String paragraph, String[] banned) {
        String[] targets = paragraph.toLowerCase()
            .split(SPECIAL);

        bannedWords.addAll(List.of(banned));

        for (String word : targets) {
            if (word.isBlank() || bannedWords.contains(word)) {
                continue;
            }

            wordsCounts.put(word, wordsCounts.getOrDefault(word, 0) + 1);
        }

        int maxCount = 0;
        String maxCountWord = "";

        for (Entry<String, Integer> entry : wordsCounts.entrySet()) {
            int count = entry.getValue();

            if (maxCount < count) {
                maxCount = count;
                maxCountWord = entry.getKey();
            }
        }

        return maxCountWord;
    }

}
