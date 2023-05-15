def solution(arr, div):
    ans = [x for x in arr if x % div == 0]
    if len(ans) == 0:
        ans.append(-1)
    return sorted(ans)