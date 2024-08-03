fun main() {
    val br = System.`in`.bufferedReader()
    val origin = br.readLine()
    val validator = PPAPValidator()
    val isPPAP = validator.validate(origin)

    println(if (isPPAP) "PPAP" else "NP")
    
    br.close()
}

private class PPAPValidator {

    private val ppap = "PPAP"
    private val stack = ArrayDeque<Char>()

    fun validate(origin: String): Boolean {
        stack.clear()

        for (c in origin) {
            stack += c
            update()
        }

        return stack.size == 1 && stack.first() == 'P'
    }

    private fun update() {
        if (stack.size < 4) {
            return
        }

        val offset = stack.size - ppap.length

        for (i in ppap.indices) {
            if (stack[offset + i] != ppap[i]) {
                return
            }
        }

        repeat(3) {
            stack.removeLast()
        }
    }
}
