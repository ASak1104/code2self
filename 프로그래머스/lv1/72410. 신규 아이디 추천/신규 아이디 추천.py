def solution(new_id):
    # level 1
    new_id = new_id.lower()
    # level 2
    for sc in '''~!@#$%^&*()=+[{]}:?,<>/''':
        new_id = new_id.replace(sc, '')
    # level 3
    while '..' in new_id:
        new_id = new_id.replace('..', '.')
    # level 4
    if len(new_id) and new_id[0] == '.':
        new_id = new_id[1:]
    if len(new_id) and new_id[-1] == '.':
        new_id = new_id[:-1]
    # level 5
    new_id = new_id if len(new_id) else 'a'
    # level 6
    if len(new_id) >= 16:
        new_id = new_id[:14] if new_id[14] == '.' else new_id[:15]
    # level 7
    new_id = new_id + new_id[-1] * (3 - len(new_id))
    return new_id