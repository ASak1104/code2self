class Solution {

    val weights: Array<Pair<Int, Int>> = arrayOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

    lateinit var visits: Array<BooleanArray>
    lateinit var lands: Array<CharArray>

    fun numIslands(grid: Array<CharArray>): Int {
        lands = grid
        visits = Array(grid.size) { BooleanArray(grid[0].size) }

        var island = 0

        for (r in grid.indices) {
            for (c in grid[0].indices) {
                if (isInvalid(r, c)) {
                    continue
                }

                travel(r, c)
                island++
            }
        }

        return island
    }

    fun travel(sr: Int, sc: Int) {
        val queue: ArrayDeque<Node> = ArrayDeque()

        queue.addLast(Node(sr, sc))
        visits[sr][sc] = true

        while (queue.isNotEmpty()) {
            val u: Node = queue.removeFirst()

            for ((rw: Int, cw: Int) in weights) {
                val v = Node(u.r + rw, u.c + cw)

                if (isInvalid(v.r, v.c)) {
                    continue
                }

                queue.addLast(v)
                visits[v.r][v.c] = true
            }
        }
    }

    fun isInvalid(r: Int, c: Int): Boolean {
        return r !in lands.indices || c !in lands[0].indices || visits[r][c] || lands[r][c] == '0'
    }
}

data class Node(val r: Int, val c: Int)
