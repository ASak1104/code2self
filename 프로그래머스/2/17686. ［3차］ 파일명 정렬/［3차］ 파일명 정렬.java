import java.util.Arrays;

class Solution {
    public String[] solution(String[] files) {
        return Arrays.stream(files)
            .sorted((a, b) -> compareFileName(a, b))
            .toArray(String[]::new);
    }
    
    private int compareFileName(String a, String b) {
        String[] sa = seperate(a);
        String[] sb = seperate(b);
        
        if (sa[0].compareTo(sb[0]) != 0) {
            return sa[0].compareTo(sb[0]);
        }
        
        int na = Integer.parseInt(sa[1]);
        int nb = Integer.parseInt(sb[1]);
        
        return Integer.compare(na, nb);
    }
    
    private String[] seperate(String s) {
        StringBuilder head = new StringBuilder();
        StringBuilder number = new StringBuilder();
        char[] chars = s.toLowerCase().toCharArray();
        int i = 0;
        
        while (!Character.isDigit(chars[i])) {
            head.append(chars[i++]);
        }
        
        while (i < s.length() && Character.isDigit(chars[i])) {
            number.append(chars[i++]);
        }
        
        return new String[] { 
            head.toString(),
            number.toString()
        };
    }
}