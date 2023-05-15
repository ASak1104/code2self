def solution(progresses, speeds):
    answer = []
    for i in range(len(speeds)):
        if progresses[i] >= 100:
            answer[-1] += 1
        else:
            day = (100 - progresses[i]) // speeds[i] + 1 \
                if (100 - progresses[i]) % speeds[i] \
                else (100 - progresses[i]) // speeds[i]
            progresses = [pro + spe * day for pro, spe in zip(progresses, speeds)]
            answer.append(1)
    return answer