import bisect as bs


def solution(info, query):
    result = [0] * len(query)
    
    info_dic = dict()
    for s in info:
        s_list = s.split()
        k, v = tuple(s_list[:-1]), int(s_list[-1])
        if k in info_dic:
            bs.insort(info_dic[k], v)
        else:
            info_dic[k] = [v]
    
    query_list = []
    for s in query:
        s_list = s.replace('-', '').replace(' and', '').split()
        query_list.append((set(s_list[:-1]), int(s_list[-1])))
    
    for i, tup in enumerate(query_list):
        for k, v_list in info_dic.items():
            if tup[0] <= set(k):
                result[i] += len(v_list) - bs.bisect_left(v_list, tup[1])
    return result