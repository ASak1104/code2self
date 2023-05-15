def solution(n, a, b):
    rnd = 1
    if a > b:
        a, b = b, a
    while not(a % 2 and a == b - 1):
        a = (a + 1) // 2
        b = (b + 1) // 2
        rnd += 1
    return rnd