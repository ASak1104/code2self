from collections import Counter


def solution(k, tangerine):
    cntr = Counter(tangerine)
    for i, entry in enumerate(cntr.most_common()):
        if k < 1:
            return i
        k -= entry[1]
    return len(cntr)