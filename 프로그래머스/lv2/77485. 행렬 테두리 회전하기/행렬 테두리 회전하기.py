def solution(rows, columns, queries):
    matrix = [[c + 1 for c in range(columns)]]
    for r in range(1, rows):
        matrix.append([c + columns for c in matrix[r - 1]])
    res = []
    for query in queries:
        res.append(spin(matrix, query))
    return res


def spin(mx, query):
    r1, c1, r2, c2 = map(lambda x: x - 1, query)
    
    mmin = temp = mx[r1][c1]
    for c in range(c1 + 1, c2):
        temp, mx[r1][c] = mx[r1][c], temp
        mmin = min(mmin, temp)
    for r in range(r1, r2):
        temp, mx[r][c2] = mx[r][c2], temp
        mmin = min(mmin, temp)
    for c in range(c2, c1, -1):
        temp, mx[r2][c] = mx[r2][c], temp
        mmin = min(mmin, temp)
    for r in range(r2, r1, -1):
        temp, mx[r][c1] = mx[r][c1], temp
        mmin = min(mmin, temp)
    mx[r1][c1] = temp
    return mmin