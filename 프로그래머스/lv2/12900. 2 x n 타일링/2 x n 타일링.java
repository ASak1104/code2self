class Solution {
    public int solution(int n) {
        int[] memo = new int[n + 1];
        memo[0] = 1;
        memo[1] = 1;
        for (int k = 2; k <= n; k++) {
            memo[k] = (memo[k - 1] + memo[k - 2]) % 1_000_000_007;
        }
        return memo[n];
    }
}