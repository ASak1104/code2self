def solution(s):
    stack = 0
    for c in s:
        if c == '(':
            stack += 1
        else:
            stack -= 1
        if stack < 0:
            return False
    return stack == 0