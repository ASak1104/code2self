from math import factorial as fac


def solution(n):
    arr = [(n - x, x // 2) for x in range(0, n + 1, 2)]
    return sum(calc(no1, no2) for no1, no2 in arr) % 1234567


def calc(num_of_one, num_of_two):
        return fac(num_of_one + num_of_two) // fac(num_of_one) // fac(num_of_two)