class Solution {

    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        var r = 0
        var c = matrix.first().lastIndex

        while (r < matrix.size && c >= 0) {
            if (matrix[r][c] == target) {
                return true
            }

            if (matrix[r][c] < target) {
                r++
            } else {
                c--
            }
        }
        
        return false
    }
}
