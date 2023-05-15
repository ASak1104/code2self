def solution(brown, yellow):
    mn = brown + yellow
    for m in range(3, int(mn ** 0.5) + 1):
        if mn % m == 0 and 2 * (mn // m + m - 2) == brown:
            return [mn // m, m]