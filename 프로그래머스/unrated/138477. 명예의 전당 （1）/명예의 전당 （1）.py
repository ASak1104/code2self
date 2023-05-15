from heapq import *


def solution(k, score):
    answer = []
    heap = []
    for v in score:
        if not heap or len(heap) < k:
            heappush(heap, v)
        elif heap[0] < v:
            heappushpop(heap, v)
        answer.append(heap[0])
    return answer