def solution(n, a, b):
    return next_round(a, b, 1)


def next_round(a, b, rnd):
    a, b = sum(divmod(a, 2)), sum(divmod(b, 2))
    if a == b:
        return rnd
    return next_round(a, b, rnd + 1)