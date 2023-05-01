## 문제

- 출처: [Leetcode](https://leetcode.com/problemset/all/)
- 문제: [121. Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/)
- 등급: Easy

<br>

## 코드 - Python

```python
from typing import *


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
```

[//]: # (<br>)

[//]: # (### 해설)
