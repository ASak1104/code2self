from collections import defaultdict as dfdict


def solution(name, yearning, photo):
    answer = []
    yearn_map = dfdict(int)
    for s, v in zip(name, yearning):
        yearn_map[s] = v
    for arr in photo:
        score = 0
        for s in arr:
            score += yearn_map[s]
        answer.append(score)
    return answer