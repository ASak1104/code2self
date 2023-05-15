from collections import Counter


def solution(X, Y):
    intersect = Counter(str(X)) & Counter(str(Y))
    if not intersect:
        return '-1'
    inter_arr = sorted(intersect.elements(), reverse=True)
    if inter_arr[0] == '0':
        return '0'
    return ''.join(inter_arr)