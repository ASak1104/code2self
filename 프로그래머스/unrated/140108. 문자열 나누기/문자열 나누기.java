class Solution {
    public int solution(String s) {
        int answer = 0;
        for (int i = 0; i < s.length();) {
            char target = s.charAt(i++);
            int tCnt = 1;
            while (tCnt != 0 && i < s.length()) {
                tCnt += s.charAt(i++) == target ? 1 : -1;
            }
            answer++;
        }
        return answer;
    }
}