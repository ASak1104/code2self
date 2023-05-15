import heapq as hq


def solution(scoville, K):
    heap = scoville[:]
    hq.heapify(heap)
    
    cnt = 0
    while len(heap) > 1:
        if heap[0] >= K:
            return cnt
        cnt += 1
        sc1, sc2 = hq.heappop(heap), hq.heappop(heap)
        hq.heappush(heap, sc1 + sc2 * 2)
    
    if heap[0] < K:
        cnt = -1
    return cnt