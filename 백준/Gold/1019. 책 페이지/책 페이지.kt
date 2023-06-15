fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    close()

    val res = Pages(n).counts

    with(System.out.bufferedWriter()) {
        res.joinTo(this, " ").flush()
        close()
    }
}

class Pages(n: Int) {
    val counts = IntArray(10)
    var s = 1
    var e = n
    var weight = 1
    val valid: Boolean
        get() = s <= e

    init {
        while (valid) {
            while (valid && s % 10 != 0) calc(s++)

            if (s > e) break

            while (valid && e % 10 != 9) calc(e--)

            s /= 10
            e /= 10

            val dist = e - s + 1

            repeat(10) { counts[it] += dist * weight }

            weight *= 10
        }
    }

    fun calc(t: Int) {
        var k = t

        while (k > 0) {
            counts[k % 10] += weight
            k /= 10
        }
    }
}