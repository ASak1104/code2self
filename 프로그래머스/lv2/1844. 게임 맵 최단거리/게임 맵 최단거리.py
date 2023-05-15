from collections import deque


def solution(maps):
    visit = [[0] * len(maps[0]) for _ in range(len(maps))]
    visit[0][0] = True
    offsets = [(1, 0), (0, 1), (-1, 0), (0, -1)]
    
    q = deque([(0, 0, 1)])
    while q:
        r, c, d = q.pop()
        for ro, co in offsets:
            rn, cn = r + ro, c + co
            if -1 < rn < len(maps) and -1 < cn < len(maps[0]):
                if maps[rn][cn] == 1 and not visit[rn][cn]:
                    if (rn, cn) == (len(maps) - 1, len(maps[0]) - 1):
                        return d + 1
                    visit[rn][cn] = True
                    q.appendleft((rn, cn, d + 1))
    return -1