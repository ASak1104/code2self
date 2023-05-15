def solution(s):
    
    def pal_len(left, right):
        if right >= len(s):
            return 1
        while left >= 0 and right < len(s) and s[left] == s[right]:
            left -= 1
            right += 1
        return right - left - 1
    
    result = 1
    for i in range(len(s) - 1):
        result = max(result, pal_len(i, i + 1), pal_len(i, i + 2))
    return result

# def solution(s):
#     if s == s[::-1] :
#         return len(s)
#     for i in range(1, len(s)) :
#         n = len(s) - i
#         for e in [x for x in range(len(s) - n + 1)] :
#             p = s[e: e + n]
#             if p == p[::-1] :
#                 return len(p)
#     return -1