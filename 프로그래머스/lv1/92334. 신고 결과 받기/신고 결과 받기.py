def solution(id_list, report, k):
    answer = [0] * len(id_list)    
    reports = {x : 0 for x in id_list}

    for r in set(report):
        reports[r.split()[1]] += 1

    for r in set(report):
        if reports[r.split()[1]] >= k:
            answer[id_list.index(r.split()[0])] += 1

    return answer

# def solution(id_list, report, k):
#     counts = {iid: 0 for iid in id_list}
#     reporters = {iid: set() for iid in id_list}
    
#     for arr in report:
#         ifrom, ito = arr.split()
#         if ifrom not in reporters[ito]:
#             counts[ito] += 1
#             reporters[ito].add(ifrom)
    
#     res = {iid: 0 for iid in id_list}
#     for iid, cnts in counts.items():
#         if cnts >= k:
#             for ifrom in reporters[iid]:
#                 res[ifrom] += 1
#     return list(res.values())