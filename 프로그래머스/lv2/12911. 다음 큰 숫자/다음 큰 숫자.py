def solution(n):
    n_bin = bin(n)[2:]
    add_len = 0
    for i in range(len(n_bin)):
        if n_bin[-(i + 1)] == '1':
            add_len = i
            break
    add = '1' + '0' * add_len
    target = bin(n + int(add, 2))[2:]
    slide_len_1 = n_bin.count('1') - target.count('1')
    if slide_len_1 == 0:
        return int(target, 2)
    return int(target[:-slide_len_1] + '1' * slide_len_1, 2)