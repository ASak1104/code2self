from collections import Counter as cntr


def solution(p, c):
    return list((cntr(p) - cntr(c)).keys())[0]