fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(' ').map(String::toInt)
    val wm = WallMap(n, m, Array(n) { readLine().map { it - '0' } })
    close()

    wm.breakWall()

    val bw = System.out.bufferedWriter()
    wm.result.forEach { row ->
        bw.append(row.joinToString(""))
        bw.newLine()
    }
    bw.flush()
    bw.close()
}

class WallMap(val n: Int, val m: Int, val map: Array<List<Int>>) {
    val moves = arrayOf(-1 to 0, 1 to 0, 0 to 1, 0 to -1)
    val visit = Array(n) { BooleanArray(m) }
    val result = Array(n) { IntArray(m) }

    fun breakWall() {
        for (r in 0 until n) {
            for (c in 0 until m) {
                if (!visit[r][c])
                    travel(r, c)
            }
        }
    }

    fun travel(sr: Int, sc: Int) {
        if (map[sr][sc] == 1) {
            result[sr][sc] = (result[sr][sc] + 1) % 10
            return
        }
        visit[sr][sc] = true
        val ones = mutableSetOf<Point>()
        val que = ArrayDeque<Point>()
        que.add(Point(sr, sc))

        var count = 1
        while (que.isNotEmpty()) {
            val (r, c) = que.removeLast()

            for ((mr, mc) in moves) {
                val adj = Point(r + mr, c + mc)

                if (adj.invalid())
                    continue

                if (map[adj.r][adj.c] == 1) {
                    ones.add(adj)
                }
                else if (!visit[adj.r][adj.c]) {
                    visit[adj.r][adj.c] = true
                    que.add(adj)
                    count++
                }
            }
        }
        ones.forEach { (r, c) -> result[r][c] = (result[r][c] + count) % 10 }
    }

    fun Point.invalid() = r !in 0 until n || c !in 0 until m
}

data class Point(val r: Int, val c: Int)