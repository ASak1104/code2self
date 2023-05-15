def solution(price, money, count):
    answer = money
    for n in range(1, count + 1):
        answer -= price * n
    answer = abs(answer) if answer < 0 else 0
    return answer