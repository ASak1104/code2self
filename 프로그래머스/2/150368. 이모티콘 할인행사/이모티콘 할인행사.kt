class Solution {
    val percents = intArrayOf(10, 20, 30, 40)
    val result = intArrayOf(0, 0)

    lateinit var users: Array<IntArray>
    lateinit var emoticons: IntArray

    fun solution(users: Array<IntArray>, emoticons: IntArray): IntArray {
        this.users = users
        this.emoticons = emoticons

        val localPercents = IntArray(emoticons.size)

        simulate(0, localPercents)

        return result
    }

    fun simulate(index: Int, localPercents: IntArray) {
        if (index == emoticons.size) {
            calulate(localPercents)

            return
        }

        for (percent in percents) {
            localPercents[index] = percent

            simulate(index + 1, localPercents)
        }
    }

    fun calulate(localPercents: IntArray) {
        val userPurchases = IntArray(users.size)

        for (i in users.indices) {
            for (j in emoticons.indices) {
                if (users[i][0] > localPercents[j]) continue

                userPurchases[i] += emoticons[j] * (100 - localPercents[j]) / 100
            }
        }

        var joinedUserCount = 0
        var profit = 0

        for (i in users.indices) {
            if (users[i][1] > userPurchases[i]) {
                profit += userPurchases[i]
            } else {
                joinedUserCount++
            }
        }

        if (joinedUserCount > result[0]) {
            result[0] = joinedUserCount
            result[1] = profit
        }

        if (joinedUserCount == result[0] && profit > result[1]) {
            result[1] = profit
        }
    }
}
