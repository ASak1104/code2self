a = [list(map(int, input().split()))]
for e in a:
    if e != [0, 0]:
        print(sum(e))
        a.append(list(map(int, input().split())))