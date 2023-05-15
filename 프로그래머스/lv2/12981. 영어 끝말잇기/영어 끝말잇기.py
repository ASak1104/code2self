from collections import Counter


def solution(n, words):
    used = Counter()
    start = words[0][0]
    for i, w in enumerate(words):
        if w[0] == start and used[w] == 0:
            start = w[-1]
            used[w] += 1
        else:
            d, m = divmod(i, n)
            return [m + 1, d + 1]
    return [0, 0]