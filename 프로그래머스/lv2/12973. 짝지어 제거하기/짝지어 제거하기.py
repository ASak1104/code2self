from collections import deque as dq


def solution(s):
    lifo = dq()
    for c in s:
        if lifo:
            if lifo[-1] == c:
                lifo.pop()
                continue
        lifo.append(c)
    return int(len(lifo) == 0)