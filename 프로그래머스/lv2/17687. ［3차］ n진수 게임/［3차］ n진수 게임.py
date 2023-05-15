def solution(n, t, m, p):
    answer = ''
    nums = ''.join([convertDecimal(x, n) for x in range(10)])
    target = 10
    offset = 0
    while len(answer) < t:
        if p + offset - 1 < len(nums):
            answer += nums[p + offset - 1]
            offset += m
        else:
            while len(nums) < offset + p:
                nums += convertDecimal(target, n)
                target += 1
            nums = nums[offset:]
            offset = 0
    return answer


def convertDecimal(n, k):
    if k == 10:
        return str(n)
    prev = ''
    div, mod = divmod(n, k)
    while div:
        prev += hex(mod)[2:]
        div, mod = divmod(div, k)
    return (hex(mod)[2:] + prev[::-1]).upper()