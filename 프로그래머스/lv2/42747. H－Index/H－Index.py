def solution(citations):
    h = len(citations)
    over = sum(1 for c in citations if c >= h)
    while h > over:
        h -= 1
        over = sum(1 for c in citations if c >= h)
    return h