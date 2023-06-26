fun main() {
    val n = readln().toInt()

    if (n < 10) return flush("$n")

    if (n > 1022) return flush("-1")

    val numbers = ArrayList<Long>(1024)

    fun choice(x: Long, start: Int, size: Int) {
        numbers.add(x)

        if (start < size) return

        for (i in 0 until x % 10) {
            choice(x * 10 + i, start, size + 1)
        }
    }

    for (x in 0..9) {
        choice(x.toLong(), x, 1)
    }

    numbers.sort()

    flush("${numbers[n]}")
}

fun flush(s: String) = with(System.out.bufferedWriter()) {
    append(s)
    flush()
    close()
}