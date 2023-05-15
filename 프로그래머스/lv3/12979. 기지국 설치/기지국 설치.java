class Solution {
    public int solution(int n, int[] stations, int w) {
        int range = 2 * w + 1;
        int answer = getStations(stations[0] - w - 1, range);
        for (int i = 0; i < stations.length - 1; i++) {
            answer += getStations(stations[i + 1] - stations[i] - 2 * w - 1, range);
        }
        answer += getStations(n - stations[stations.length - 1] - w, range);
        return answer;
    }

    private int getStations(int gap, int range) {
        if (gap < 1) {
            return 0;
        }
        int stats = gap / range;
        if (gap % range > 0) {
            stats++;
        }
        return stats;
    }
}