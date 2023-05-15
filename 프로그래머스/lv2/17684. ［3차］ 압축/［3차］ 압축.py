def solution(msg):
    numberList = []
    
    cache = dict()
    for asc in range(ord('A'), ord('Z') + 1):
        cache[chr(asc)] = asc - 64
    
    left = 0
    while left < len(msg):
        offset = 2
        while left + offset <= len(msg) and msg[left: left + offset] in cache:
            offset += 1
        newWord = msg[left: left + offset]
        if newWord in cache:
            numberList.append(cache[newWord])
        else:
            cache[newWord] = len(cache) + 1
            numberList.append(cache[newWord[:-1]])
        left += offset - 1
    
    return numberList