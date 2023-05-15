from collections import deque
from bisect import *


def solution(operations):
    answer = [0, 0]
    queue = deque()
    for op in operations:
        cmd, digit = op.split()
        digit = int(digit)
        if cmd == 'I':
            insort(queue, digit)
            continue
        if not queue:
            continue
        if digit > 0:
            queue.pop()
        else:
            queue.popleft()
    if queue:
        answer = [queue[-1], queue[0]]
    return answer