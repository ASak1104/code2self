def solution(s):
    c = list(s)
    i = 1
    for idx in range(len(c)):
        if c[idx] == ' ':
            i = 1
            continue
        c[idx] = c[idx].upper() if i % 2 else c[idx].lower()
        i += 1
    return ''.join(c)