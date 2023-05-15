def solution(left, right):
    return sum([-n if n ** 0.5 == int(n ** 0.5) else n for n in range(left, right + 1)])