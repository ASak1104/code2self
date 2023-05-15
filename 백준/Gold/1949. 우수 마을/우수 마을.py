import sys
input = sys.stdin.readline
sys.setrecursionlimit(20000)

n = int(input())
w = [0] + list(map(int, input().split()))
e = [[] for i in range(n + 1)]
visit = [False for i in range(n + 1)]
dp = [[0] * 2 for i in range(n + 1)]

def dfs(start):
    visit[start] = True
    dp[start][0] = w[start]
    for i in e[start]:
        if not visit[i]:
            dfs(i)
            dp[start][0] += dp[i][1]
            dp[start][1] += max(dp[i][0], dp[i][1])

for i in range(n - 1):
    a, b = map(int, input().split())
    e[a].append(b)
    e[b].append(a)

dfs(1)
print(max(dp[1][0], dp[1][1]))