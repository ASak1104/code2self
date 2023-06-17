import sys

input = sys.stdin.readline


def monotone_chain(points):
    stack = []

    for next_point in points:
        while len(stack) > 1:
            second = stack.pop()
            first = stack[-1]

            if ccw(first, second, next_point) >= 0:
                stack.append(second)
                break

        stack.append(next_point)

    stack.pop()
    return stack


def ccw(p, p1, p2):
    return (p1[0] - p[0]) * (p2[1] - p[1]) - (p2[0] - p[0]) * (p1[1] - p[1])


n = int(input())
points = []

for _ in range(n):
    x, y, c = map(str, input().split())
    if c == "Y":
        points.append((int(x), int(y)))

points.sort()
lower = monotone_chain(points)

points.reverse()
upper = monotone_chain(points)

print(len(lower) + len(upper))
for p in lower + upper:
    print(*p)