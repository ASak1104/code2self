from functools import cmp_to_key as ctk


def solution(files):
    return sorted(files, key=ctk(cmpFileName))


def cmpFileName(fn1, fn2):
    h1 = h2 = 1
    while not fn1[h1].isdigit():
        h1 += 1
    while not fn2[h2].isdigit():
        h2 += 1
    head1, head2 = fn1[:h1].lower(), fn2[:h2].lower()
    if head1 < head2:
        return -1
    if head1 > head2:
        return 1
    
    n1, n2 = h1 + 1, h2 + 1
    while n1 < len(fn1) and n1 - h1 < 6 and fn1[n1].isdigit():
        n1 += 1
    while n2 < len(fn2) and n2 - h2 < 6 and fn2[n2].isdigit():
        n2 += 1
    return int(fn1[h1: n1]) - int(fn2[h2: n2])