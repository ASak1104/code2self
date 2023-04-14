memo = {'A': 0, 'E': 1, 'I': 2, 'O': 3, 'U': 4}
offset = [781, 156, 31, 6, 1]


def solution(word):
    order = len(word)
    for i, c in enumerate(word):
        order += offset[i] * memo[c]
    return order
