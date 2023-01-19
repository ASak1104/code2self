def solution(p):
    if isValidBracket(p):
        return p
    u, v = divideBracket(p)
    if isValidBracket(u):
        return u + solution(v)
    temp = ['(' if c == ')' else ')' for c in u[1:-1]]
    return f'({solution(v)})' + ''.join(temp)


def isValidBracket(p):
    stack = []
    for c in p:
        if c == ')':
            if not stack:
                return False
            stack.pop()
        else:
            stack.append(c)
    return True


def divideBracket(p):
    count = 0
    for i, c in enumerate(p):
        if c == '(':
            count += 1
        else:
            count -= 1
        if count == 0:
            return p[:i + 1], p[i + 1:]
    return p


s1 = solution("(()())()")
print(s1)
s2 = solution(")(")
print(s2)
s3 = solution("()))((()")
print(s3)

