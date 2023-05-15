from functools import cmp_to_key


def solution(numbers):
    strs = list(map(str, numbers))
    strs.sort(key=cmp_to_key(compare), reverse=True)
    return str(int(''.join(strs)))


def compare(a, b):
    return int(a + b) - int(b + a)