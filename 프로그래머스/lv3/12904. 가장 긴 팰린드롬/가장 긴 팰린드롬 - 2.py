def solution(s):
    if s == s[::-1] :
        return len(s)
    for i in range(1, len(s)) :
        n = len(s) - i
        for e in [x for x in range(len(s) - n + 1)] :
            p = s[e: e + n]
            if p == p[::-1] :
                return len(p)
    return -1