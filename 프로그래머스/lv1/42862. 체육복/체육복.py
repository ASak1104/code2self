def solution(n, lost, res):
    lost, res = list(set(lost) - set(res)), list(set(res) - set(lost))
    arr = [0 if x + 1 in lost else 1 for x in range(n)]
    for i in res:
        if i != 1 and arr[i - 2] == 0:
            arr[i - 2] = 1
        elif i != n and arr[i] == 0:
            arr[i] = 1
    return sum(arr)