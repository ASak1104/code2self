def solution(ans):
    arr = [[[1, 2, 3, 4, 5], 0], 
         [[2, 1, 2, 3, 2, 4, 2, 5], 0], 
         [[3, 3, 1, 1, 2, 2, 4, 4, 5, 5], 0]]
    for s_arr in arr:
        for i in range(len(ans)):
            if s_arr[0][i % len(s_arr[0])] == ans[i]:
                s_arr[1] += 1
    rst = {i + 1 : arr[i][1] for i in range(3)}
    return sorted([x for x in rst.keys() if rst[x] == max(rst.values())])