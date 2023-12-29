class Solution {

    val edges: Array<ArrayList<Int>> = Array(2000) { arrayListOf() }
    val inDegrees: IntArray = IntArray(2000)

    var n: Int = 0

    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        n = numCourses

        for ((u, v) in prerequisites) {
            edges[u] += v
            inDegrees[v]++
        }

        return isCompletableCourses()
    }

    fun isCompletableCourses(): Boolean {
        val deque: ArrayDeque<Int> = ArrayDeque(n)

        var completableCourse: Int = 0

        for (u in 0 until n) {
            if (inDegrees[u] == 0) {
                deque += u
            }
        }

        while (deque.isNotEmpty()) {
            val u = deque.removeFirst()

            for (v in edges[u]) {
                if (--inDegrees[v] == 0) {
                    deque += v
                }
            }

            completableCourse++
        }

        return completableCourse == n
    }
}
