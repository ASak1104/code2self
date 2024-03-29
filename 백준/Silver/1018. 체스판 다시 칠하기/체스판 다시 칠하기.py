from sys import stdin

n, m = map(int, stdin.readline().split())
board = [stdin.readline().rstrip() for _ in range(n)]

answer = 32
chess = "WB" * 4
for r in range(n - 7):
    for c in range(m - 7):
        w_min = 0
        for i in range(8):
            for j in range(8):
                if board[r + i][c + j] != chess[j]:
                    w_min += 1
            chess = chess[::-1]
        answer = min(answer, w_min, 64 - w_min)
print(answer)