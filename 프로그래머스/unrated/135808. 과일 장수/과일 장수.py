def solution(k, m, score):
    answer = 0
    score.sort(reverse=True)
    for i in range(1, len(score) // m + 1):
        answer += m * score[m * i - 1]
    return answer