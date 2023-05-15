def solution(s):
    res = len(s)
    for size in range(1, len(s) // 2 + 1):
        temp = len(s)
        cnt = 1
        for i in range(size, len(s) + 1, size):
            if s[i - size:i] == s[i:i + size]:
                cnt += 1
            else:
                if cnt != 1:
                    temp = temp - size * (cnt - 1) + len(str(cnt))
                cnt = 1
        res = min(res, temp)
    return res