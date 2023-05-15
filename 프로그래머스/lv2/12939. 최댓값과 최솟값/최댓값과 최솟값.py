def solution(s):
    a = list(map(int, s.split()))
    return f'{min(a)} {max(a)}'