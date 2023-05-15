def solution(n, m, section):
    count = 0
    roller = 1
    for v in section:
        if roller <= v:
            roller = v + m
            count += 1
    return count