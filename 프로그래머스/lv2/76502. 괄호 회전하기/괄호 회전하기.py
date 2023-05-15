from collections import deque

leftBracket = {"(", "{", "["}
rightBracket = {")": '(', "}": "{", "]": "["}


def solution(s):
    answer = 0
    brackets = deque(s)
    for _ in range(len(s)):
        answer += int(isValidBracket(brackets))
        brackets.rotate(-1)
    return answer


def isValidBracket(brackets):
    stack = []
    for bracket in brackets:
        if bracket in leftBracket:
            stack.append(bracket)
        else:
            if not stack or stack[-1] != rightBracket[bracket]:
                return False
            stack.pop()
    return len(stack) == 0