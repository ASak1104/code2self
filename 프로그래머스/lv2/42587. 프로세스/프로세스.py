from collections import deque


def solution(priorities, location):
    que = deque([(i, priorities[i]) for i in range(len(priorities))])
    cnt = 1
    while que:
        pMax = max(que, key=lambda x:x[1])[1]
        e = que.popleft()
        if e[1] == pMax:
            if e[0] == location:
                return cnt
            cnt += 1
        else:
            que.append(e)
    return -1