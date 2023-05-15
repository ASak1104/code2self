from collections import deque as dq


def solution(maps):
    n, m = len(maps), len(maps[0])
    moves = [(1, 0), (0, 1), (-1, 0), (0, -1)]
    q = dq([(0, 0, 1)])

    while q:
        r, c, cnt = q.popleft()
        if r == n - 1 and c == m - 1:
            return cnt
        for x, y in moves:
            if 0 <= r + x < n and 0 <= c + y < m:
                if maps[r + x][c + y]:
                    q.append((r + x, c + y, cnt + 1))
                    maps[r + x][c + y] = 0
    return -1