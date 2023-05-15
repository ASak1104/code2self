def solution(elements):
    elems = elements * 2
    ans = set(elements)
    for lg in range(2, len(elements)):
        for s in range(len(elements)):
            ans.add(sum(elems[s: s + lg]))
    ans.add(sum(elements))
    return len(ans)