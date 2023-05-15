def solution(dartResult):
    sliceIndex = [0] 
    for i in range(1, len(dartResult)) :
        if (not dartResult[i - 1].isdigit() and dartResult[i].isdigit()):
            sliceIndex.append(i)
    sliceIndex.append(None)

    scoreList = [dartResult[sliceIndex[i]: sliceIndex[i + 1]] for i in range(len(sliceIndex) - 1)]
    for i in range(len(scoreList)) :
        if scoreList[i][-1] == '*' :
            if i < 1 :
                scoreList[i] = '2 * ' + scoreList[i][:-1]
            else :
                scoreList[i] = '2 * ' + scoreList[i][:-1]
                scoreList[i - 1] = '2 * ' + scoreList[i - 1]

    temp = [eval(e.replace('S', '').replace('D', ' ** 2').replace('T', ' ** 3').replace('#', ' * -1')) \
            for e in scoreList]
    return sum(temp)