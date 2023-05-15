from collections import Counter


def solution(survey, choices):
    mbtiDict = Counter()
    for content, choice in zip(survey, choices):
        if content[0] < content[1]:
            mbtiDict[content] += choice - 4
        else:
            mbtiDict[content[::-1]] += 4 - choice
    answer = list()
    for content in ['RT', 'CF', 'JM', 'AN']:
        if mbtiDict[content] <= 0:
            answer.append(content[0])
        else:
            answer.append(content[1])
    return ''.join(answer)