def solution(cards1, cards2, goal):
    cards1 = cards1[::-1]
    cards2 = cards2[::-1]
    for word in goal:
        if cards1 and word == cards1[-1]:
            cards1.pop()
        elif cards2 and word == cards2[-1]:
            cards2.pop()
        else:
            return 'No'
    return 'Yes'
