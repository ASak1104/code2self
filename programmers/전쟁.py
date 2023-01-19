from collections import Counter, deque

visit = set()
offset_list = [(0, 1), (1, 0), (-1, 0), (0, -1)]


def solution(maps):
    total_dict = Counter()
    for r in range(len(maps)):
        for c in range(len(maps[0])):
            if maps[r][c] != '.' and (r, c) not in visit:
                res = war(maps, r, c)
                winner = max(res.keys(), key=lambda x: (res[x], x))
                bound = res[winner]
                for country in res.keys():
                    if res[country] < bound:
                        res[winner], res[country] = res[winner] + res[country], 0
                total_dict += res
    return total_dict.most_common(1)[0][1]


def war(maps, rs, cs):
    country_dict = Counter()
    pos_queue = deque([(rs, cs)])
    visit.add((rs, cs))
    while pos_queue:
        r, c = pos_queue.popleft()
        country_dict[maps[r][c]] += 1
        for ro, co in offset_list:
            rn, cn = r + ro, c + co
            if -1 < rn < len(maps) and -1 < cn < len(maps[0]):
                if maps[rn][cn] != '.' and (rn, cn) not in visit:
                    pos_queue.append((rn, cn))
                    visit.add((rn, cn))
    return country_dict


print(solution(["AABCA.QA", "AABC..QX", "BBBC.Y..", ".A...T.A", "....EE..", ".M.XXEXQ", "KL.TBBBQ"]))
