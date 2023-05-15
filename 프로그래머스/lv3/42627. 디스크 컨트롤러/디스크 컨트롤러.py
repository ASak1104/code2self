from heapq import *


def solution(jobs):
    answer = now = cnt = 0
    start = -1
    heap = []
    
    while cnt < len(jobs):
        for res, cost in jobs:
            if start < res <= now:
                heappush(heap, (cost, res))
        if heap:
            cost, res = heappop(heap)
            start = now
            now += cost
            answer += now - res
            cnt += 1
        else:
            now += 1
    return answer // len(jobs)