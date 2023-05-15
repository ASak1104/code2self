def solution(s, n):
    upp = list(map(chr, range(ord('A'), ord('Z') + 1)))
    low = list(map(chr, range(ord('a'), ord('z') + 1)))
    caesar = lambda x, y, n : x[(x.index(y) + n) % len(x)]
    return ''.join([caesar(upp, x, n) if x in upp else (caesar(low, x, n) if x in low else ' ') for x in s])