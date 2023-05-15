def solution(dirs):
    def is_in(x, y):
        return -5 <= x <= 5 and -5 <= y <= 5
    memo = set()
    move = {'U': (0, 1), 'D': (0, -1), 'R': (1, 0), 'L': (-1, 0)}
    res = 0

    pos = (0, 0)
    for d in dirs:
        x, y = move[d]
        pos_next = (pos[0] + x, pos[1] + y)
        if not is_in(pos_next[0], pos_next[1]):
            continue
        if (pos, (pos_next)) not in memo:
            memo |= {(pos, pos_next), (pos_next, pos)}
            res += 1
        pos = pos_next

    return res