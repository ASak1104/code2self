import re


def solution(skill, skill_trees):
    return sum(is_valid(skill, st) for st in skill_trees)


def is_valid(skill, st):
    for i, c in enumerate(re.findall(f'[{skill}]', st)):
        if skill[i] != c:
            return 0
    return 1