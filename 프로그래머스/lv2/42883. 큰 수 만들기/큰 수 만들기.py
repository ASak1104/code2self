def solution(number, k):
    stack = [number[0]]    
    i = 1
    while k and i < len(number):
        if number[i] <= stack[-1]:
            stack.append(number[i])
        else:
            while stack and k and stack[-1] < number[i]:
                stack.pop()
                k -= 1
            stack.append(number[i])
        i += 1
    return ("".join(stack) + number[i:])[:len(number) - k]