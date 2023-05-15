def solution(N, stages):
    cnt, perc = [0] * (N + 2), []
    for e in stages:
        cnt[e] += 1
    print(cnt)
    var = cnt[-1]
    for i in range(N, 0, -1):
        var += cnt[i]
        perc.append((i, cnt[i] / var)) if var else perc.append((i, 0))
    perc.sort(key=lambda x : (-x[1], x[0]))
    print(perc)
    return [x[0] for x in perc]