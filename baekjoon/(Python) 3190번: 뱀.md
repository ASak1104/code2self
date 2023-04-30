## 문제

- 출처: [백준](https://www.acmicpc.net)
- 문제: [3190번: 뱀](https://www.acmicpc.net/problem/3190)
- 등급: 골드 4

<br>

## 코드 - Python

```python
from sys import stdin
from collections import deque


class Snake:
    def __init__(self, maps):
        self.routes = {0: (0, 1), 1: (1, 0), 2: (0, -1), 3: (-1, 0)}
        self.direction = 0
        self.head_row, self.head_col = (1, 1)
        self.body = deque([(1, 1)])
        self.moves = 0
        self.maps = maps
        self.maps[1][1] = 1

    def turn(self, curve):
        self.direction = (self.direction + curve) % 4

    def crawl(self, dist):
        for _ in range(self.moves, dist):
            self.moves += 1
            self.head_row += self.routes[self.direction][0]
            self.head_col += self.routes[self.direction][1]

            if self.maps[self.head_row][self.head_col] == 1:
                return True

            self.body.appendleft((self.head_row, self.head_col))
            if self.maps[self.head_row][self.head_col] == 0:
                tail_row, tail_col = self.body.pop()
                self.maps[tail_row][tail_col] = 0
            self.maps[self.head_row][self.head_col] = 1
        return False

    def crawl_to_end(self):
        self.crawl(self.moves + len(self.maps))

    def game_over(self):
        print(self.moves)


if __name__ == '__main__':
    N = int(stdin.readline())
    maps = [[1] * (N + 2)]
    maps += [[1] + [0] * N + [1] for _ in range(N)]
    maps += [[1] * (N + 2)]

    K = int(stdin.readline())
    for _ in range(K):
        row, col = map(int, stdin.readline().split())
        maps[row][col] = 2

    L = int(stdin.readline())
    controls = []
    for _ in range(L):
        dist, curve = stdin.readline().split()
        controls.append((int(dist), 1 if curve == 'D' else 3))

    snake = Snake(maps)
    for dist, curve in controls:
        if snake.crawl(dist):
            break
        snake.turn(curve)
    else:
        snake.crawl_to_end()
    snake.game_over()
```

[//]: # (<br>)

[//]: # (### 해설)
