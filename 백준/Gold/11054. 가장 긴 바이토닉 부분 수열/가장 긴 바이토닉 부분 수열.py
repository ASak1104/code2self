from sys import stdin
from bisect import bisect_left


def update_scale_buffer(scale: list, buffer: list, i: int) -> None:
    if buffer[-1] < seq[i]:
        scale[i] += len(buffer)
        buffer.append(seq[i])
    else:
        idx = bisect_left(buffer, seq[i])
        buffer[idx] = min(buffer[idx], seq[i])
        scale[i] += idx


N = int(stdin.readline())
seq = list(map(int, stdin.readline().split()))

up_scale = [1] * N
down_scale = [1] * N

up_buffer = seq[:1]
down_buffer = seq[-1:]
for up, down in zip(range(1, N), range(N - 2, -1, -1)):
    update_scale_buffer(up_scale, up_buffer, up)
    update_scale_buffer(down_scale, down_buffer, down)

res = 1
for up, down in zip(up_scale, down_scale):
    res = max(res, up + down - 1)
print(res)