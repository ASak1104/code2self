const val START = "JFK"

class Solution {

    val edgeLists: MutableMap<String, ArrayDeque<String>> = mutableMapOf()
    val answer: ArrayList<String> = arrayListOf()

    fun findItinerary(tickets: List<List<String>>): List<String> {
        for (ticket in tickets) {
            val (u, v) = ticket

            edgeLists.getOrPut(u) { ArrayDeque() } += v
            edgeLists.getOrPut(v) { ArrayDeque() }
        }

        edgeLists.values.forEach { it.sort() }

        travel(START)

        answer.reverse()

        return answer
    }

    fun travel(u: String) {
        val edges: ArrayDeque<String> = edgeLists[u]!!

        while (edges.isNotEmpty()) {
            travel(edges.removeFirst())
        }

        answer += u
    }
}
