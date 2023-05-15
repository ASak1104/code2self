def solution(s, times = 0, counts = 0):
    if s == '1' :
        return [times, counts]
    times += 1
    counts += s.count('0')
    return solution(bin(len(s.replace('0', '')))[2:], times, counts)