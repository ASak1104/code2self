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

    fun travel(r: Int, c: Int) {
        visits[r][c] = true

        for ((rw, cw) in weights) {
            if (isInvalid(r + rw, c + cw)) {
                continue
            }

            travel(r + rw, c + cw)
        }
    }

    fun isInvalid(r: Int, c: Int): Boolean {
        return r !in lands.indices || c !in lands[0].indices || visits[r][c] || lands[r][c] == '0'
    }
}
