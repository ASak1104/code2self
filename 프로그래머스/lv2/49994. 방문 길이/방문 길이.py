def solution(dirs):
    memo = {'U': (0, 1), 'D': (0, -1), 'L': (-1, 0), 'R': (1, 0)}
    visit = set()
    
    result = 0
    x, y = 0, 0
    for d in dirs:
        tx, ty = memo[d][0], memo[d][1]
        if not (-5 <= x + tx <= 5 and -5 <= y + ty <= 5):
            continue
        if not {((x, y), (x + tx, y + ty)), ((x + tx, y + ty), (x, y))} <= visit:
            visit |= {((x, y), (x + tx, y + ty)), ((x + tx, y + ty), (x, y))}
            result += 1
        x += tx
        y += ty
        
    return result