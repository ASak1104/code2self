import sys

readline = sys.stdin.readline


def build_tree(n):
    capacity = 1
    while capacity < n:
        capacity = capacity << 1
    ret = [0] * (capacity << 1)
    ret[0] = capacity  # index offset
    return ret


def update(tree, y):
    node = tree[0] + y
    while node > 0:
        tree[node] += 1
        node = node >> 1


def query(tree, qs, qe):
    start = tree[0] + qs
    end = tree[0] + qe
    ret = 0
    while start < end:
        if start % 2 == 1:
            ret += tree[start]
            start += 1
        if end % 2 == 0:
            ret += tree[end]
            end -= 1
        start = start >> 1
        end = end >> 1
    if start == end:
        ret += tree[start]
    return ret


results = []
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
    tree = build_tree(count + 1)
    for _, y in islands:
        answer += query(tree, y, count)
        update(tree, y)
    results.append(str(answer))

print('\n'.join(results))