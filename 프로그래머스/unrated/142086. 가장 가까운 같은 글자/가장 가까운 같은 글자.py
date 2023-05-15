from collections import defaultdict as dfdict


def solution(s):
    memo = dfdict(lambda: -1)
    answer = []
    for i, c in enumerate(s):
        target = memo[c]
        if target < 0:
            answer.append(target)
        else:
            answer.append(i - target)
        memo[c] = i
    return answer