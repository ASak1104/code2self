from re import match
from collections import Counter as ct


def solution(str1, str2):
    ms1, ms2 = map(get_multiset, [str1, str2])
    sum_set = sum((ms1 | ms2).values())
    if not sum_set:
        return 65536
    inter_set = sum((ms1 & ms2).values())
    return int(inter_set / sum_set * 65536)


def get_multiset(string):
    ms = ct()
    string = string.lower()
    for i in range(len(string) - 1):
        if match('[a-z][a-z]', string[i: i + 2]):
            ms[string[i: i + 2]] += 1
    return ms