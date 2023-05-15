from itertools import permutations as pm


def solution(exp):
    return max(map(abs, map(calc, [exp] * 6, map(list, pm('*+-', 2)))))


def count_oper(exp):
    return len(set(c for c in exp if c in {'*', '+', '-'}))


def calc(exp, splitter):
    if len(splitter) == 0 or count_oper(exp) < 2:
        return eval(exp)
    
    operator = splitter.pop()
    exp_next = exp.split(operator)
    values = list(map(calc, exp_next, [splitter[:] for _ in range(len(exp_next))]))
    res = values[0]
    for val in values[1:]:
        if operator == '+':
            res += val
        elif operator == '-':
            res -= val
        else:
            res *= val
    return res