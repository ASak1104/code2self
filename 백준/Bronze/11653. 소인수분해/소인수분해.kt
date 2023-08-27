fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    var n = br.readLine().toInt()

    for (p in 2..n) {
        if (n == 1) break

        while (n % p == 0) {
            n /= p
            bw.append("$p\n")
        }
    }

    bw.flush()
    bw.close()
    br.close()
}