from sys import stdin

def solution(n, c, w_set):
    if c in w_set:
        return 1
    for w1 in w_set:
        if c - w1 != w1 and c - w1 in w_set:
            return 1
        for w2 in w_set:
            if w1 != w2 and c - w1 - w2 not in (w1, w2) and c - w1 - w2 in w_set:
                return 1
    return 0

n, c = map(int, stdin.readline().split())
w_set = set(map(int, stdin.readline().split()))
print(solution(n, c, w_set))