def solution(s):
    result = [s[0]]
    for i in range(len(s)-1):
        if str(s[i]) != str(s[i+1]):
            result.append(s[i+1])
    return result