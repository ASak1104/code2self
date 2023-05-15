def solution(sizes):
    r_max = c_max = 0
    for size in sizes:
        c, r = sorted(size)
        r_max = max(r_max, r)
        c_max = max(c_max, c)
    return r_max * c_max