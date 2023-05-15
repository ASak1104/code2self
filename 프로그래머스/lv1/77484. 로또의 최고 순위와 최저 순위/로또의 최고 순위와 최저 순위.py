def solution(lottos, win_nums):
    cnt_zero = lottos.count(0)
    cnt_lotto = sum([1 for num in lottos if num in win_nums])
    memo = [6] + [7 - i for i in range(1, 7)]
    return [memo[cnt_lotto + cnt_zero], memo[cnt_lotto]]