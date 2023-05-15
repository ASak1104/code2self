from sys import stdin

n = int(stdin.readline())
memo = [0] * (n + 1)

for p in range(2, n + 1):
    memo[p] = memo[p - 1] + 1
    if p % 2 == 0:
        memo[p] = min(memo[p], memo[p // 2] + 1)
    if p % 3 == 0:
        memo[p] = min(memo[p], memo[p // 3] + 1)
print(memo[n])