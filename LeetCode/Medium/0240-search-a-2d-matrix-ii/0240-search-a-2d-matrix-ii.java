class Solution {

    int[][] matrix;
    boolean found;

    public boolean searchMatrix(int[][] matrix, int target) {
        this.matrix = matrix;

        int colBound = searchRow(0, 0, matrix[0].length - 1, target);
        int rowBound = searchCol(0, 0, Math.min(matrix.length - 1, colBound), target);

        for (int r = 0; r <= rowBound; r++) {
            for (int c = 0; c <= colBound; c++) {
                if (found) {
                    return true;
                }

                searchRow(r, 0, c, target);
            }
        }

        return found;
    }

    int searchRow(int row, int left, int right, int target) {
        int bound = matrix[0].length - 1;

        while (left <= right) {
            int mid = (left + right) >>> 1;
            int value = matrix[row][mid];

            if (value == target) {
                found = true;

                return mid;
            }

            if (value < target) {
                left = mid + 1;
            } else {
                bound = mid;
                right = mid - 1;
            }
        }

        return bound;
    }

    int searchCol(int col, int left, int right, int target) {
        int bound = matrix.length - 1;

        while (left <= right) {
            int mid = (left + right) >>> 1;
            int value = matrix[mid][col];

            if (value == target) {
                found = true;

                return mid;
            }

            if (value < target) {
                left = mid + 1;
            } else {
                bound = mid;
                right = mid - 1;
            }
        }

        return bound;
    }

}
