def solution(routes):
    ans = 0
    pre_end = -30001
    for s, e in sorted(routes, key=lambda x: x[1]):
        if s <= pre_end:
            continue
        pre_end = e
        ans += 1
    return ans