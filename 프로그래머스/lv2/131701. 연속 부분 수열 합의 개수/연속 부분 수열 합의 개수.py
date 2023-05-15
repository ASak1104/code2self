# def solution(elements):
#     elems = elements * 2
#     ans = set(elements)
#     for lg in range(2, len(elements)):
#         for s in range(len(elements)):
#             ans.add(sum(elems[s: s + lg]))
#     ans.add(sum(elements))
#     return len(ans)


def solution(elements):
    answer = set(elements + [sum(elements)])
    for lg in range(2, len(elements)):
        answer |= comb(elements, lg)
    return len(answer)


def comb(elems, lg):
    subs = set()
    for i in range(len(elems)):
        if i + lg > len(elems):
            subs.add(sum(elems[i: len(elems)]) + sum(elems[: i + lg - len(elems)]))
        else:
            subs.add(sum(elems[i: i + lg]))
    return subs