from math import factorial as fact
from collections import deque as dq


def solution(n, k):
    res = []
    people = dq([i for i in range(n, 0, -1)])
    cnt = 0
    while len(people) > 1:
        person = people.pop()
        seq = fact(len(people))
        if seq < k:
            people.appendleft(person)
            k -= seq
            cnt += 1
        else:
            res.append(person)
            people.rotate(-cnt)
            cnt = 0
    return res + [people.pop()]