class AC {
    private var arr = IntArray(0)
    private var start = 0
    private var end = -1
    private var isReverse = false

    fun setArray(array: IntArray) {
        arr = array
        end = arr.lastIndex
    }

    fun clear() {
        arr = IntArray(0)
        start = 0
        end = -1
        isReverse = false
    }

    fun reverse() {
        isReverse = !isReverse
    }

    fun drop() {
        if (isReverse) {
            end--
        } else {
            start++
        }
    }

    fun isEmpty(): Boolean {
        return start > end
    }

    override fun toString(): String {
        if (isEmpty()) {
            return "[]"
        }
        val sub = arr.sliceArray(start..end)
        if (isReverse) {
            sub.reverse()
        }
        return sub.joinToString(",", "[", "]")
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()
    val ac = AC()
    val numOfTest = readLine().toInt()
    outer@ for (i in 0 until numOfTest) {
        val cmds = readLine()
        val length = readLine().toInt()
        val line = readLine()
        if (length != 0) {
            ac.setArray(line.substring(1, line.lastIndex).split(',').map { it.toInt() }.toIntArray())
        }
        for (cmd in cmds) {
            if (cmd == 'R') {
                ac.reverse()
            } else {
                if (ac.isEmpty()) {
                    sb.append("error\n")
                    ac.clear()
                    continue@outer
                }
                ac.drop()
            }
        }
        sb.append(ac, "\n")
        ac.clear()
    }
    print(sb.trimEnd())
}