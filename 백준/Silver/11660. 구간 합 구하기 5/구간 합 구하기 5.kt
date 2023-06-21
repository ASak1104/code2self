import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val readInt = { nextToken(); nval.toInt() }
    val n = readInt()
    val m = readInt()
    val tabel = Array(n + 1) { IntArray(n + 1) }

    repeat(n) { r ->
        repeat(n) { c->
            tabel[r + 1][c + 1] = readInt() + tabel[r + 1][c] + tabel[r][c + 1] - tabel[r][c]
        }
    }

    val bw = System.out.bufferedWriter()

    repeat(m) {
        val rs = readInt()
        val cs = readInt()
        val re = readInt()
        val ce = readInt()

        val res = tabel[re][ce] - tabel[re][cs - 1] - tabel[rs - 1][ce] + tabel[rs - 1][cs - 1]

        bw.append("$res\n")
    }
    bw.flush()
    bw.close()
}