def solution(pb):
    prefix_set = set()
    for p in sorted(pb, key=len, reverse=True):
        if p in prefix_set:
            return False
        for i in range(1, len(p)):
            prefix_set.add(p[:i])
    return True