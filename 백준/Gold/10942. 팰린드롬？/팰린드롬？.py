import sys

input = sys.stdin.readline

n = int(input())
seq = list(map(int, input().split()))
dp = [[False] * n for _ in range(n)]

for i in range(n - 1):
    dp[i][i] = True
    dp[i][i + 1] = seq[i] == seq[i + 1]
dp[-1][-1] = True

for s in range(n - 3, -1, -1):
    for e in range(s + 2, n):
        dp[s][e] = dp[s + 1][e - 1] and seq[s] == seq[e]

ans = []
for _ in range(int(input())):
    s, e = map(int, input().split())
    ans.append(int(dp[s - 1][e - 1]))
print(*ans, sep='\n', flush=True)