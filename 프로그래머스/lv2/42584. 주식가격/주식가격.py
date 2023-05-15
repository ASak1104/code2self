def solution(p):
    ans = []
    for i in range(len(p) - 1):
        j = i + 1
        while p[i] <= p[j] and j < len(p) - 1:
            j += 1
        ans.append(j - i)
    ans.append(0)
    return ans