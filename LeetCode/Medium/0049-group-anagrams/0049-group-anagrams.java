import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

class Solution {

    private final TrieNode root = new TrieNode();

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> group = new ArrayList<>();
        int idx = 0;
        int blankCount = 0;

        for (String s : strs) {
            if (s.isBlank()) {
                blankCount++;
                continue;
            }

            char[] chars = s.toCharArray();
            TrieNode node = root;

            Arrays.sort(chars);

            for (char c : chars) {
                node = node.get(c);
            }

            if (node.getIdx() == -1) {
                node.setIdx(idx++);
                group.add(new ArrayList<>());
            }

            group.get(node.getIdx()).add(s);
        }

        if (blankCount > 0) {
            List<String> blanks = new ArrayList<>();

            for (int i = 0; i < blankCount; i++) {
                blanks.add("");
            }

            group.add(blanks);
        }

        return group;
    }

}

class TrieNode {

    private final TrieNode[] nodes = new TrieNode[26];

    private int idx = -1;

    public TrieNode get(char c) {
        if (Objects.isNull(nodes[c - 'a'])) {
            nodes[c - 'a'] = new TrieNode();
        }

        return nodes[c - 'a'];
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getIdx() {
        return idx;
    }

}
