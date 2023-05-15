def solution(arr1, arr2):
    answer = [[0] * len(arr2[0]) for _ in range(len(arr1))]
    for n in range(len(answer)):
        for m in range(len(answer[0])):
            for k in range(len(arr2)):
                answer[n][m] += arr1[n][k] * arr2[k][m]
    return answer