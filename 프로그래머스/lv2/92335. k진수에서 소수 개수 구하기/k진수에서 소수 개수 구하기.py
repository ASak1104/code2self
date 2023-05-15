from collections import Counter


def solution(n, k):
    convN = convertDecimal(n, k)
    convDict = Counter([int(x) for x in convN.split('0') if x and int(x) > 1])
    return sum([value for key, value in convDict.items() if isPrime(key)])


def convertDecimal(n, k):
    if k >= 10:
        return str(n)
    prev = ''
    div, mod = divmod(n, k)
    while div:
        prev += str(mod)
        div, mod = divmod(div, k)
    return str(mod) + prev[::-1]


def isPrime(n):
    for i in range(2, int(n ** 0.5) + 1):
        if n % i == 0:
            return False
    return True