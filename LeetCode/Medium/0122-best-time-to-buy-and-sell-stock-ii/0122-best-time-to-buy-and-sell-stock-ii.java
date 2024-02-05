class Solution {

    public int maxProfit(int[] prices) {
        int profit = 0;
        int min = prices[0];

        for (int price : prices) {
            profit += Math.max(price - min, 0);
            min = price;
        }

        return profit;
    }

}
