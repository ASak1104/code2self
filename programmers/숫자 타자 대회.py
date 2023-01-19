import sys

sys.setrecursionlimit(1000000)

dial = {'0': (3, 1)}
for i in range(9):
    dial[str(i + 1)] = divmod(i, 3)


def solution(_numbers: str) -> int:
    global numbers
    numbers = _numbers
    global memo
    memo = {i: {} for i in range(len(numbers))}
    return dfs(0, '4', '6')


def dfs(i: int, l: str, r: str) -> int:
    if i == len(numbers):
        return 0
    if l > r:
        l, r = r, l
    if numbers[i] in (l, r):
        memo[i][(l, r)] = 1 + dfs(i + 1, l, r)
    if (l, r) not in memo[i]:
        lw = get_weight(numbers[i], l)
        rw = get_weight(numbers[i], r)
        memo[i][(l, r)] = min(lw + dfs(i + 1, numbers[i], r),
                              rw + dfs(i + 1, l, numbers[i]))
    return memo[i][(l, r)]


def get_weight(s1: str, s2: str) -> int:
    p1, p2 = dial[s1], dial[s2]
    mx = abs(p1[0] - p2[0])
    mn = abs(p1[1] - p2[1])
    if mx < mn:
        mx, mn = mn, mx
    return 2 * mx + mn