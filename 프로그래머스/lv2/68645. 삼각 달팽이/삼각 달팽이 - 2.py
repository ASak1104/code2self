def solution(n):
    if n == 1:
        return [1]
    arr_2d = [[i] + [0] * (i - 2) + [n * 3 - (i + 1)] for i in range(1, n)]
    arr_2d[0] = [1]
    arr_2d.append([n + i for i in range(n)])
    
    r, c, k = 2, 1, n * 3 - 2
    end = sum(range(n + 1))
    while k <= end:
        while arr_2d[r][c] == 0:
            arr_2d[r][c] = k
            r += 1
            k += 1
        r -= 1
        c += 1
        while arr_2d[r][c] == 0:
            arr_2d[r][c] = k
            c += 1
            k += 1
        c -= 2
        r -= 1
        while arr_2d[r][c] == 0:
            arr_2d[r][c] = k
            r -= 1
            c -= 1
            k += 1
        r += 2
        c += 1
    return sum(arr_2d, [])