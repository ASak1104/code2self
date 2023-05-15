from collections import Counter as ct


def solution(s):
    cnts = ct()
    strs = s.replace('{', '').replace('}}', '').split('},')
    for arr in strs:
        cnts += ct(arr.split(','))
    return list(map(lambda x: int(x[0]), cnts.most_common()))