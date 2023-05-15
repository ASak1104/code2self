memo = {"aya", "ye", "woo", "ma"}


def solution(babbling):
    answer = 0
    for bab in babbling:
        prev = ''
        i = 0
        while i < len(bab):
            case1, case2 = bab[i: i + 2], bab[i: i + 3]
            if prev in (case1, case2):
                break
            if case1 in memo:
                prev = case1
                i += 2
            elif case2 in memo:
                prev = case2
                i += 3
            else:
                break
        else:
            answer += 1
    return answer