def solution(n):
    answer = 0
    set_list = [{(row, 0)} for row in range(n)]
    for point_set in set_list:
        answer += solve(n, point_set)
    return answer


def solve(n, point_set):
    result = 0
    if len(point_set) == n:
        result = 1
    for row in range(n):
        if is_safe(row, len(point_set), point_set):
            result += solve(n, point_set | {(row, len(point_set))})
    return result


def is_safe(r, c, point_set):
    for rx, cx in point_set:
        if rx == r or cx == c or abs(rx - r) == abs(cx - c):
            return False
    return True