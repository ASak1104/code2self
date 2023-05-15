def solution(number, limit, power):
    weight = 0
    for k in range(1, number + 1):
        divs = num_divisors(k)
        if divs > limit:
            divs = power
        weight += divs
    return weight

def num_divisors(k):
    count = -int((k ** 0.5).is_integer())
    for i in range(1, int(k ** 0.5) + 1):
        if k % i == 0:
            count += 2
    return count