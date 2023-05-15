def solution(n):
    if n == 1:
        return [1]
    ltm = [] # lower triangular matrix
    for r in range(1, n + 1):
        ltm.append([0 for _ in range(r)])
    triangle_snail(ltm, 0, 0, n - 1, 1)
    return sum(ltm, [])


def triangle_snail(mx, rows, cols, k, offset):
    for r in range(rows, rows + k):
        mx[r][cols] = offset
        offset += 1
    for c in range(cols, cols + k):
        mx[rows + k][c] = offset
        offset += 1
    for rc in range(k, 0, -1):
        mx[rows + rc][cols + rc] = offset
        offset += 1
    if k > 3:
        triangle_snail(mx, rows + 2, cols + 1, k - 3, offset)
    elif k == 3:
        mx[rows + 2][cols + 1] = offset