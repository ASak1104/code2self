import sys

sys.setrecursionlimit(1000000)


def solution(land):
    global memo
    memo = [[0] * 4 for _ in range(len(land))]
    for c in range(4):
        travel(land, 0, c)
    return max(memo[0])


def travel(land, r, c):
    if r == len(land):
        return 0
    if memo[r][c] < 1:
        temp = 0
        for i in range(4):
            if i == c:
                continue
            temp = max(temp, land[r][c] + travel(land, r + 1, i))
        memo[r][c] = temp
    return memo[r][c]