import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()
    var set = 0

    repeat(readLine().toInt()) {
        val st = StringTokenizer(readLine())
        val cmd = st.nextToken()

        if (!st.hasMoreTokens()) {
            set = if (cmd == "all")
                (1 shl 20) - 1
            else
                0
            return@repeat
        }
        val x = 1 shl (st.nextToken().toInt() - 1)

        when (cmd) {
            "add" -> set = set or x
            "remove" -> set = set and x.inv()
            "check" -> bw.append("${if(set and x == x) 1 else 0}\n")
            else -> set = set xor x
        }
    }
    bw.flush()
    bw.close()
    close()
}