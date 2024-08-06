import java.util.ArrayList;
import java.util.List;

class Solution {

    private String[] ids;
    private String[] contents;
    private List<Integer> letterIndices;
    private List<Integer> digitIndices;

    public String[] reorderLogFiles(String[] logs) {
        init(logs);
        sortingLetterIds();

        letterIndices.addAll(digitIndices);

        return letterIndices.stream()
            .map(i -> logs[i])
            .toArray(String[]::new);
    }

    private void init(String[] logs) {
        ids = new String[logs.length];
        contents = new String[logs.length];
        letterIndices = new ArrayList<>();
        digitIndices = new ArrayList<>();

        for (int i = 0; i < logs.length; i++) {
            String log = logs[i];
            int sepIdx = log.indexOf(' ');
            String id = log.substring(0, sepIdx);
            String content = log.substring(sepIdx + 1);

            ids[i] = id;
            contents[i] = content;

            if (Character.isDigit(content.charAt(0))) {
                digitIndices.add(i);
            } else {
                letterIndices.add(i);
                contents[i] = content;
            }
        }
    }

    private void sortingLetterIds() {
        letterIndices.sort((a, b) -> {
            if (contents[a].equals(contents[b])) {
                return ids[a].compareTo(ids[b]);
            }

            return contents[a].compareTo(contents[b]);
        });
    }

}
