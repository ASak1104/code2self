from collections import Counter as ct

def solution(gems):
    k = len(set(gems))
    gset = ct()
    start, end = 1, len(gems)
    
    left, right = 0, 0
    while right < len(gems):
        gset[gems[right]] += 1
        right += 1
        if len(gset) == k:
            while left < right and gset[gems[left]] > 1:
                gset[gems[left]] -= 1
                left += 1
            if right - left <= end - start:
                start, end = left + 1, right
    return start, end