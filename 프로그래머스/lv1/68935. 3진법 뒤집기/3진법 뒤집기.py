def solution(n):
    q, r = divmod(n, 3)
    answer = str(r)
    
    while q:
        q, r = divmod(q, 3)
        answer += str(r)
        
    return int(answer, 3)