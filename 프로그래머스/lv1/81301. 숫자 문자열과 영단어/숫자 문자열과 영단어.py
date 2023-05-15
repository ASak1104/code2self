def solution(s):
    memo = {
        'zero': '0',
        'one': '1',
        'two': '2',
        'three': '3',
        'four': '4',
        'five': '5',
        'six': '6',
        'seven': '7',
        'eight': '8',
        'nine': '9',
    }
    temp = s
    for word in memo.keys():
        try:
            return int(s)
        except:
            s = s.replace(word, memo[word])
    return int(s)