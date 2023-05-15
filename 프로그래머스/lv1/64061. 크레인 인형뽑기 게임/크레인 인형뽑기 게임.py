def solution(board, moves):
    n, rst, rvs = len(board), [-1], list(map(list, zip(*board)))
    for idx_m in moves:
        for idx_r in range(n):
            if rvs[idx_m - 1][idx_r]:
                if rst[-1] == rvs[idx_m - 1][idx_r]:
                    del rst[-1]
                    rvs[idx_m - 1][idx_r] = 0
                    break
                else:
                    rst.append(rvs[idx_m - 1][idx_r])
                    rvs[idx_m - 1][idx_r] = 0
                    break
        else:
            rst.insert(0, -1)
    return len(moves) - len(rst) + 1