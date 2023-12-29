import java.util.*

const val START = "JFK"

class Solution {

    val edgeLists: MutableMap<String, PriorityQueue<String>> = HashMap(300)
    val answer: ArrayList<String> = ArrayList(300)

    fun findItinerary(tickets: List<List<String>>): List<String> {
        for ((u, v) in tickets) {
            edgeLists.getOrPut(u) { PriorityQueue() } += v
            edgeLists.getOrPut(v) { PriorityQueue() }
        }

        travel(START)

        answer.reverse()

        return answer
    }

    fun travel(u: String) {
        val edges: PriorityQueue<String> = edgeLists[u]!!

        while (edges.isNotEmpty()) {
            travel(edges.poll())
        }

        answer += u
    }
}
