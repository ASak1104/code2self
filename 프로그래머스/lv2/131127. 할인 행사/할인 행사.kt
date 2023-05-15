class Solution {
    val wantMap = mutableMapOf<String, Int>()
    val countMap = mutableMapOf<String, Int>()

    fun solution(want: Array<String>, number: IntArray, discount: Array<String>): Int {
        for (i in 0 until want.size) {
            wantMap[want[i]] = number[i]
        }

        val total = number.sum()
        for (i in 0 until total - 1) {
            val product = discount[i]
            if (wantMap.containsKey(product)) {
                countMap[product] = countMap.getOrDefault(product, 0) + 1
            }
        }

        var answer: Int = 0
        for (i in total - 1 until discount.size) {
            val new = discount[i]
            if (wantMap.containsKey(new)) {
                countMap[new] = countMap.getOrDefault(new, 0) + 1
            }
            
            answer += isSatisfy()
            
            val old = discount[i - total + 1]
            if (wantMap.containsKey(old)) {
                countMap[old] = Math.max(countMap[old]!!, 1) - 1
            }
        }
        return answer
    }

    fun isSatisfy(): Int {
        for (entry in wantMap) {
            if (countMap.getOrDefault(entry.key, 0) < entry.value) {
                return 0
            }
        }
        return 1;
    }
}