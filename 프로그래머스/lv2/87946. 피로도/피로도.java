class Solution {
    private int[][] dungeons;

    public int solution(int k, int[][] dungeons) {
        this.dungeons = dungeons;
        return travelDungeons(k, 0);
    }

    private int travelDungeons(int k, int visitedBit) {
        int count = Integer.bitCount(visitedBit);
        for (int i = 0; i < dungeons.length; i++) {
            if ((1 << i & visitedBit) == 1 << i)
                continue;
            if (k < dungeons[i][0])
                continue;
            count = Math.max(count, travelDungeons(k - dungeons[i][1], visitedBit | 1 << i));
        }
        return count;
    }
}