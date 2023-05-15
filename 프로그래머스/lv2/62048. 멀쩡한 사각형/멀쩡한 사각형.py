from math import ceil, gcd


def solution(w, h):
    a = gcd(w, h)
    k = h // a + w // a
    return w * h - (k - 1) * a