import java.util.*;
import java.util.stream.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = Arrays.stream(numbers)
            .mapToObj(Integer::toString)
            .sorted((a, b) -> compare(b, a))
            .collect(Collectors.joining());
        
        if (answer.startsWith("0")) {
            return "0";
        }
        
        return answer;
    }
    
    private int compare(String a, String b) {
        int adx = 0;
        int bdx = 0;
        
        while (adx < a.length() && bdx < b.length()) {
            char ac = a.charAt(adx++);
            char bc = b.charAt(bdx++);
            
            if (ac != bc) {
                return Character.compare(ac, bc);
            }
        }
        
        int sab = Integer.parseInt(a + b);
        int sba = Integer.parseInt(b + a);
        
        return Integer.compare(sab, sba);
    }
}
