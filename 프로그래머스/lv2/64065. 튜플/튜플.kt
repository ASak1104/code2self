class Solution {
    val map = HashMap<Int, Int>()

    fun solution(s: String): IntArray {
        val split = s.replace("{", "").replace("}}", "").split("},")
        
        split.forEach { e ->
            e.split(',').forEach {
                val v = it.toInt()

                map[v] = (map[v] ?: 0) + 1
            }
        }

        return map.keys.sortedBy { -map[it]!! }.toIntArray()
    }
}