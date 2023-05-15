def solution(stones, k):
    answer = 0
    k_min, k_max = 0, 200_000_000
    while k_min <= k_max:
        k_mid = (k_min + k_max) >> 1
        if isValidK(stones, k, k_mid):
            answer = max(answer, k_mid)
            k_min = k_mid + 1
        else:
            k_max = k_mid - 1
    return answer


def isValidK(stones, k, k_mid):
    count = 0
    for stone in stones:
        if stone < k_mid:
            count += 1
            if count == k:
                return False
        else:
            count = 0
    return True