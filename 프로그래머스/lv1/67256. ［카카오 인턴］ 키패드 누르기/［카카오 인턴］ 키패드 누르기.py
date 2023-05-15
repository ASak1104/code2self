def solution(numbers, hand):
    num_map = [(1, 3)]
    for c in range(3):
        for r in range(3):
            num_map.append((r, c))
    lh, rh = (0, 3), (2, 3)
    result = ''
    
    for num in numbers:
        if num_map[num][0] == 0:
            lh = num_map[num]
            result += 'L'
        elif num_map[num][0] == 2:
            rh = num_map[num]
            result += 'R'
        else:
            dis_left = abs(num_map[num][0] - lh[0]) + abs(num_map[num][1] - lh[1])
            dis_right = abs(num_map[num][0] - rh[0]) + abs(num_map[num][1] - rh[1])
            if dis_left < dis_right or (dis_left == dis_right and hand == 'left'):
                lh = num_map[num]
                result += 'L'
            else:
                rh = num_map[num]
                result += 'R'
    return result