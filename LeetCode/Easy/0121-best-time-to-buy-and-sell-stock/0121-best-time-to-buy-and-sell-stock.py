class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        profit = 0
        buy = prices[0]
        for i in range(1, len(prices)):
            if profit < prices[i] - buy:
                profit = prices[i] - buy
            if buy > prices[i]:
                buy = prices[i]
        return profit