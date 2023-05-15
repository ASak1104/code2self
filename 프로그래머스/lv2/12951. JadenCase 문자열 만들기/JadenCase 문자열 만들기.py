def solution(s):
    answer = ''
    for word in s.lower().split(' '):
        if word and word[0].isalpha():
            word = word[0].upper() + word[1:]
        answer += word + ' '
    return answer[:-1]