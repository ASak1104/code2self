class Solution {

    lateinit var deliveries: IntArray
    lateinit var pickups: IntArray

    var last = 0
    var cap = 0
    var dist = 0L
    
    fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
        this.deliveries = deliveries
        this.pickups = pickups
        this.last = n - 1
        this.cap = cap

        while (last >= 0) {
            simulate()
        }

        return dist
    }

    fun simulate() {
        while (last >= 0 && deliveries[last] == 0 && pickups[last] == 0) {
            last--
        }

        if (last < 0) {
            return
        }

        val lastMin = minOf(deliveries[last], pickups[last])
        
        if (cap <= lastMin) {
            val p = lastMin / cap
            
            deliveries[last] -= p * cap
            pickups[last] -= p * cap
            dist += 2L * (last + 1) * p
            
            return
        }

        drive(deliveries)
        drive(pickups)
    }

    fun drive(array: IntArray) {
        var count = cap
        var house = last

        while (house >= 0 && count > 0) {
            if (count >= array[house]) {
                count -= array[house]
                array[house] = 0
            } else {
                array[house] -= count
                count = 0
            }

            house--
        }

        dist += last + 1
    }
}
