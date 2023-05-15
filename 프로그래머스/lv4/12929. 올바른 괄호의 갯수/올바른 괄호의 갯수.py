memo = [1, 1, 2, 5]
def solution(n):
    if n < len(memo):
        return memo[n]
    else:
        temp = 0
        for i in range(n):
            temp += solution(i) * solution(n - 1 - i)
        memo.append(temp)
        return memo[n]