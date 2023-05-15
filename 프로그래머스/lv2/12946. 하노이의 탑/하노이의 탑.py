def solution(n):
    answer = []
    hanoi(answer, n, 1, 2, 3)
    return answer


def hanoi(answer, n, start, mid, end):
    if n == 1:
        answer.append([start, end])
    else:
        hanoi(answer, n - 1, start, end, mid)
        answer.append([start, end])
        hanoi(answer, n - 1, mid, start, end)