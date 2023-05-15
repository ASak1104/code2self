class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visitedNode = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visitedNode[i])
                continue;
            travel(i, computers, visitedNode);
            answer += 1;
        }
        return answer;
    }

    private void travel(int node, int[][] computers, boolean[] visitedNode) {
        visitedNode[node] = true;
        for (int i = 0; i < visitedNode.length; i++) {
            if (computers[node][i] == 0 || visitedNode[i])
                continue;
            travel(i, computers, visitedNode);
        }
    }
}