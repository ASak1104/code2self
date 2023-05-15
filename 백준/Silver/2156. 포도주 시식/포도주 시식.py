from sys import stdin

n = int(stdin.readline())
wine = [int(stdin.readline()) for _ in range(n)]

memo = [wine[0], sum(wine[:2])] + [0] * (n - 2)
for i in range(2, n):
    memo[i] = max(memo[i - 1], memo[i - 2] + wine[i], memo[i - 3] + wine[i - 1] + wine[i])
print(memo[n - 1])