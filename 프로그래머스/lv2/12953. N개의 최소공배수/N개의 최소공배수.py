from math import gcd


def solution(arr):
    answer = 1
    for n in arr:
        if answer % n != 0:
            answer = answer * n // gcd(answer, n)
    return answer