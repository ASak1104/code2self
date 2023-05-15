def solution(record):
    res = []
    data = dict()
    for string in record:
        if string[0] != 'L':
            command, user, name = string.split()
            data[user] = name
    for string in record:
        if string[0] == 'E':
            command, user, name = string.split()
            res.append(f'{data[user]}님이 들어왔습니다.')
        elif string[0] == 'L':
            command, user = string.split()
            res.append(f'{data[user]}님이 나갔습니다.')
    return res