from collections import deque, defaultdict


def solution(n, wires):
    answer = n
    tree = defaultdict(set)
    for u, v in wires:
        tree[u].add(v)
        tree[v].add(u)

    for n1, n2 in wires:
        dq = deque([n1])
        visit = {n1, n2}
        while dq:
            u = dq.popleft()
            for v in tree[u]:
                if v in visit:
                    continue
                visit.add(v)
                dq.append(v)
        answer = min(answer, abs(n - 2 * len(visit) + 2))
    return answer
