import java.io.StreamTokenizer

private val st = StreamTokenizer(System.`in`.bufferedReader())

private fun readInt(): Int {
    st.nextToken()
    return st.nval.toInt()
}

fun main() {
    val n = readInt()
    val wires = Wires(n)

    repeat(n) {
        wires.addWire(readInt(), readInt())
    }

    wires.build()

    with(System.out.bufferedWriter()) {
        val res = wires.getInvalidWire()

        append("${res.size}\n")

        res.joinTo(this, "\n")

        flush()
        close()
    }
}

class Wires(val n: Int) {
    val seqs = ArrayList<Wire>(n)
    val counts = IntArray(n)
    val lis = ArrayList<Wire>(n ushr 1)
    val lisMax get() = lis.last().end

    fun addWire(a: Int, b: Int) {
        seqs += Wire(a, b)
    }

    fun build() {
        seqs.sortBy(Wire::start)
        lis += seqs[0]
        counts[0] = 1

        for (i in 1 until n) {
            val wire = seqs[i]

            if (wire.end > lisMax) {
                lis += wire
                counts[i] = lis.size
                continue
            }

            val idx = bisect(wire.end)

            lis[idx] = wire
            counts[i] = idx + 1
        }
    }

    fun getInvalidWire(): IntArray {
        var target = lis.size
        val ret = IntArray(n - target)
        var j = ret.lastIndex

        for (i in n - 1 downTo 0) {
            if (counts[i] == target) {
                target--
            } else {
                ret[j--] = seqs[i].start
            }
        }

        return ret
    }

    fun bisect(v: Int): Int {
        var s = 0
        var e = lis.lastIndex

        while (s <= e) {
            val mid = (s + e) ushr 1

            if (lis[mid].end == v) return mid

            if (lis[mid].end < v) {
                s = mid + 1
            } else {
                e = mid - 1
            }
        }

        return s
    }

    class Wire(val start: Int, val end: Int)
}