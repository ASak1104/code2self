from collections import Counter as ct
from math import prod


def solution(clothes):
    cnts = ct(t for n, t in clothes)
    temp = cnts + ct(cnts.keys())
    return prod(temp.values()) - 1