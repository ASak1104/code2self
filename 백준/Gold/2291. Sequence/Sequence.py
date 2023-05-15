from sys import stdin

def partition(n, k):
    if n < k:
        return 0
    if k == 1 or n == k + 1:
        return 1
    if k == 2:
        return n // 2
    if (n, k) in memo:
        return memo[(n, k)]
    res = partition(n - 1, k - 1) + partition(n - k, k)
    memo[(n, k)] = res
    return res

N, M, K = map(int, stdin.readline().split())
memo = dict()

ans = [1] * N
for base_index in range(N - 1):
    base = ans[base_index]
    while True:
        p = 0
        for i in range(1, N - base_index):
            p += partition(M - sum(ans), i)
        if K <= p or not p:
            break
        K -= p
        base += 1
        for i in range(base_index, N):
            ans[i] = base
ans[-1] += M - sum(ans)
print(' '.join(map(str, ans)))