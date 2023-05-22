import sys

readline = sys.stdin.readline


def update(tree, node, start, end, y):
    if y < start or y > end:
        return
    tree[node] += 1
    if start == end:
        return
    child = (node << 1) + 1
    mid = (start + end) >> 1
    if y <= mid:
        update(tree, child, start, mid, y)
    else:
        update(tree, child + 1, mid + 1, end, y)


def query(tree, node, start, end, qs, qe):
    if end < qs or start > qe:
        return 0
    if qs <= start and end <= qe:
        return tree[node]
    child = (node << 1) + 1
    mid = (start + end) >> 1
    return query(tree, child, start, mid, qs, qe) + query(tree, child + 1, mid + 1, end, qs, qe)


for _ in range(int(readline())):
    n = int(readline())
    islands = [list(map(int, readline().split())) for _ in range(n)]

    islands.sort(key=lambda x: x[1], reverse=True)
    count = 0
    for i in range(n - 1, 0, -1):
        if islands[i][1] == islands[i - 1][1]:
            islands[i][1] = count
        else:
            islands[i][1] = count
            count += 1
    islands[0][1] = count
    islands.sort(key=lambda x: x[0])

    answer = 0
    tree = [0] * ((count + 1) << 2)
    for _, y in islands:
        answer += query(tree, 0, 0, count, y, count)
        update(tree, 0, 0, count, y)
    print(answer)