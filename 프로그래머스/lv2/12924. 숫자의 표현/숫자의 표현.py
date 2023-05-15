def solution(n):
    answer = 1
    for i in range(1, (n >> 1) + 1):
        j = i + 1
        while i < n:
            i, j = i + j, j + 1
        if i == n:
            answer += 1
    return answer