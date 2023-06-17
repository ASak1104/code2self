import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()
    val m = readLine().toInt()
    val set = mutableSetOf<Int>()

    repeat(m) {
        val st = StringTokenizer(readLine())
        val get = { st.nextToken().toInt() }

        when (st.nextToken()) {
            "add" -> set += get()
            "remove" -> set -= get()
            "check" -> bw.append("${if(get() in set) 1 else 0}\n")
            "toggle" -> {
                when (val token = get()) {
                    in set -> set -= token
                    else -> set += token
                }
            }
            "all" -> set += 1..20
            else -> set.clear()
        }
    }
    bw.flush()
    bw.close()
    close()
}