def solution(arr):
    m = min(arr)
    return len(arr) != 1 and [x for x in arr if x != m] or [-1]