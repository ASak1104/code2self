import java.io.StreamTokenizer

val st = StreamTokenizer(System.`in`.bufferedReader())

fun readInt() = with(st) {
    nextToken()
    nval.toInt()
}

fun main() = with(System.out.bufferedWriter()) {
    val testCount = readInt()
    val cholagi = Attacker()

    repeat(testCount) {
        val n = readInt()
        val w = readInt()

        cholagi.prepare(n, w)
        append("${cholagi.result()}\n")
    }

    flush()
    close()
}

class Attacker {
    val MAX_SIZE = 10000
    val outer = IntArray(MAX_SIZE)
    val inner = IntArray(MAX_SIZE)
    val a = IntArray(MAX_SIZE) 		// both case
    val b = IntArray(MAX_SIZE) 		// outer case
    val c = IntArray(MAX_SIZE) 		// inner case
    var n = 1
    var w = 1

    fun prepare(n: Int, w: Int) {
        this.n = n
        this.w = w

        repeat(n) { inner[it] = readInt() }
        repeat(n) { outer[it] = readInt() }
    }

    fun result(): Int {
        a[0] = 0
        b[0] = 1
        c[0] = 1

        var ret = attack(0)

        if (n == 1) return ret

        val innerCase = inner[n - 1] + inner[0] <= w
        val outerCase = outer[n - 1] + outer[0] <= w

        if (innerCase) {
            a[0] = 2
            a[1] = 2
            b[1] = if (outer[0] + outer[1] <= w) 2 else 3
            c[1] = 3

            attack(1)

            ret = minOf(ret, b[n - 1])
        }

        if (outerCase) {
            a[0] = 2
            a[1] = 2
            b[1] = 3
            c[1] = if (inner[0] + inner[1] <= w) 2 else 3

            attack(1)

            ret = minOf(ret, c[n - 1])
        }

        if (innerCase && outerCase) {
            a[0] = 2
            a[1] = 2
            b[1] = 3
            c[1] = 3

            attack(1)

            ret = minOf(ret, a[n - 1])
        }

        return ret
    }

    fun attack(s: Int): Int {
        for (i in s until n - 1) {
            a[i + 1] = minOfBothCase(i)
            b[i + 1] = a[i + 1] + 1
            c[i + 1] = a[i + 1] + 1

            if (outer[i] + outer[i + 1] <= w) {
                b[i + 1] = minOf(b[i + 1], c[i] + 1)
            }

            if (inner[i] + inner[i + 1] <= w) {
                c[i + 1] = minOf(c[i + 1], b[i] + 1)
            }
        }

        return minOfBothCase(n - 1)
    }

    fun minOfBothCase(i: Int): Int {
        var ret = minOf(b[i], c[i]) + 1

        // vertical case
        if (inner[i] + outer[i] <= w) {
            ret = minOf(ret, a[i] + 1)
        }

        if (i == 0) return ret

        val innerCase = inner[i - 1] + inner[i] <= w
        val outerCase = outer[i - 1] + outer[i] <= w

        // horizontal case
        return if (innerCase && outerCase)
            minOf(ret, a[i - 1] + 2)
        else
            ret
    }
}