from itertools import combinations as cb


def solution(numbers):
    return sorted(list(set(map(sum, cb(numbers, 2)))))