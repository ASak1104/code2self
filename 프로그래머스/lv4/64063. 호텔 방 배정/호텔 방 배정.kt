class Solution {
    val parents = HashMap<Long, Long>(200_000)

    fun solution(k: Long, room_number: LongArray): LongArray {
        val n = room_number.size
        val ret = LongArray(n) { findRoom(room_number[it]) }

        return ret
    }

    fun findRoom(a: Long): Long {
        if (a !in parents) {
            parents[a] = a
            return a
        }

        val next = findRoom(parents[a]!! + 1)

        parents[a] = next

        return next
    }
}