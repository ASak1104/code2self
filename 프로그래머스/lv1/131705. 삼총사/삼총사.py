from itertools import combinations as cb

def solution(number):
    return list(map(sum, cb(number, 3))).count(0)