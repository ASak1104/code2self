arr = [list(map(int, input().split()))]
for v in arr:
    print(sum(v))
    try:
        arr.append(list(map(int, input().split())))
    except:
        pass