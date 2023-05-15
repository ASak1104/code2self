def solution(absols, signs):
    return sum([n if b else -n for n, b in zip(absols, signs)])