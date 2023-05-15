def solution(n):
    a = []
    while n:
        if str(n % 3) != '0':
            a.insert(0, str(n % 3))
        else:
            a.insert(0, '4')
            n -= 1
        n //= 3
    return ''.join(a)