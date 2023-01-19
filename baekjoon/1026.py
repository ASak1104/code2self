from sys import stdin

n = int(stdin.readline())
a = list(map(int, stdin.readline().split()))
b = list(map(int, stdin.readline().split()))

answer = 0
for e1, e2 in zip(sorted(a), sorted(b, reverse=True)):
    answer += e1 * e2
print(answer)
