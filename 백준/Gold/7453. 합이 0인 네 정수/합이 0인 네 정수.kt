import java.io.InputStreamReader
import java.io.StreamTokenizer

var st = StreamTokenizer(InputStreamReader(System.`in`))

fun readInt() = with(st) {
    nextToken()
    nval.toInt()
}

fun main() {
    val n = readInt()
    val arr1 = IntArray(n)
    val arr2 = IntArray(n)
    val arr3 = IntArray(n)
    val arr4 = IntArray(n)

    repeat(n) {
        arr1[it] = readInt()
        arr2[it] = readInt()
        arr3[it] = readInt()
        arr4[it] = readInt()
    }

    val nn = n * n
    val sums1 = IntArray(nn)
    val sums2 = IntArray(nn)

    var i = 0

    for (v1 in arr1) {
        for (v2 in arr2) {
            sums1[i++] = v1 + v2
        }
    }

    var j = 0

    for (v1 in arr3) {
        for (v2 in arr4) {
            sums2[j++] = v1 + v2
        }
    }

    sums1.sort()
    sums2.sort()

    var res = 0L

    i = 0
    j = nn - 1

    while (i < nn && j >= 0) {
        while (i < nn && sums1[i] + sums2[j] < 0) i++
        while (i < nn && j >= 0 && sums1[i] + sums2[j] > 0) j--

        if (i >= nn || j < 0) break

        if (sums1[i] + sums2[j] == 0) {
            val t1 = sums1[i]
            val t2 = sums2[j]
            var cnt1 = 0
            var cnt2 = 0

            while (i < nn && sums1[i] == t1) {
                cnt1++
                i++
            }

            while (j >= 0 && sums2[j] == t2) {
                cnt2++
                j--
            }

            res += 1L * cnt1 * cnt2
        }
    }

    with(System.out.bufferedWriter()) {
        append("$res")
        flush()
        close()
    }
}