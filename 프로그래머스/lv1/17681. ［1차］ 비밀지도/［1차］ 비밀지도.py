def solution(n, arr1, arr2):
    answer = []
    for e1, e2 in zip(arr1, arr2):
        answer.append(bin(e1 | e2)[2:].zfill(n).replace('0', ' ').replace('1', '#'))
    return answer