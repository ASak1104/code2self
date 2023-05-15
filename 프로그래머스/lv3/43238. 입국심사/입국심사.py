def solution(n, times):
    s, e = 0, min(times) * n
    while s < e:
        mid = (s + e) >> 1
        total = total_time(times, mid)
        if total >= n:
            e = mid
        else:
            s = mid + 1
    return e


def total_time(times, target):
    total = 0
    for time in times:
        total += target // time
    return total