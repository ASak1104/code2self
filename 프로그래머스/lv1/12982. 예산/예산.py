def solution(d, budget):
    for i, e in enumerate(sorted(d)):
        budget -= e
        if budget < 0:
            return i
    return len(d)