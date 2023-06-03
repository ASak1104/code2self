fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val lis = LIS(n, readLine().split(' ').map(String::toInt))
    print(lis.length)
    close()
}

class LIS(n: Int, seq: List<Int>) {
    private val counts = IntArray(n) { 1 }
    private val buffer = arrayListOf(seq[0])
    private var longestIndex = 0
    val length get() = buffer.size

    init {
        for (i in 1 until n) {
            update(i, seq[i])
        }
    }

    private fun update(i: Int, target: Int) {
        if (buffer[buffer.lastIndex] < target) {
            buffer.add(target)
            counts[i] = buffer.size
            longestIndex = i
        } else {
            val idx = bisect(target)
            if (buffer[idx] > target) {
                buffer[idx] = target
            }
            counts[i] += idx
        }
    }

    private fun bisect(t: Int): Int {
        var s = 0
        var e = buffer.lastIndex

        while (s < e) {
            val mid = (s + e) ushr 1
            val v = buffer[mid]

            if (v == t) return mid

            if (v < t) {
                s = mid + 1
            } else {
                e = mid
            }
        }
        return e
    }
}