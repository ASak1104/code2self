from sys import stdin


def decompress(s, idx):
    result = 0
    while idx < len(s):
        if s[idx] == ')':
            return result
        if s[idx] == '(':
            result += int(s[idx - 1]) * decompress(s, idx + 1) - 1
            count = 1
            while count > 0:
                idx += 1
                if s[idx] == '(':
                    count += 1
                elif s[idx] == ')':
                    count -= 1
        else:
            result += 1
        idx += 1
    return result


s = stdin.readline().strip()
print(decompress(s, 0))