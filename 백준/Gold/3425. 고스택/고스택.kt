import java.io.StreamTokenizer
import kotlin.math.abs

val st = StreamTokenizer(System.`in`.bufferedReader())
val bw = System.out.bufferedWriter()
const val MAX = 1e9.toInt()

fun readLong() = with(st) {
    nextToken()
    nval.toLong()
}

fun readString() = with(st) {
    nextToken()
    sval!!
}

fun main() {
    val goStack = GoStack()
    val cmds = ArrayList<Command>(100_000)

    do {
        val cmd = Command(readString())

        if (cmd.cmd == "NUM") cmd.v = readLong()

        cmds += cmd

        if (cmd.cmd == "END") {
            repeat(readLong().toInt()) {
                goStack.start(cmds, readLong())
            }

            bw.newLine()
            cmds.clear()
        }

    } while (cmd.cmd != "QUIT")

    bw.flush()
    bw.close()
}

class GoStack {
    val stack = ArrayDeque<Long>(1000)

    fun start(cmds: List<Command>, v: Long) {
        stack.addLast(v)

        var res = true

        for (cmd in cmds) {
            res = exec(cmd)

            if (!res) break
        }

        bw.append("${if (res) stack.last() else "ERROR"}\n")

        stack.clear()
    }

    fun exec(cmd: Command): Boolean {
        when (cmd.cmd) {
            "NUM" -> {
                stack.addLast(cmd.v)
            }

            "POP" -> {
                if (stack.isEmpty()) return false

                stack.removeLast()
            }

            "INV" -> {
                if (stack.isEmpty()) return false

                stack.addLast(-stack.removeLast())
            }

            "DUP" -> {
                if (stack.isEmpty()) return false

                stack.addLast(stack.last())
            }

            "SWP" -> {
                if (stack.size < 2) return false

                val first = stack.removeLast()
                val second = stack.removeLast()

                stack.addLast(first)
                stack.addLast(second)
            }

            "ADD" -> {
                if (stack.size < 2) return false

                val first = stack.removeLast()
                val second = stack.removeLast()
                val value = first + second

                if (abs(value) > MAX) return false

                stack.addLast(value)
            }

            "SUB" -> {
                if (stack.size < 2) return false

                val first = stack.removeLast()
                val second = stack.removeLast()
                val value = second - first

                if (abs(value) > MAX) return false

                stack.addLast(value)
            }

            "MUL" -> {
                if (stack.size < 2) return false

                val first = stack.removeLast()
                val second = stack.removeLast()
                val value = first * second

                if (abs(value) > MAX) return false

                stack.addLast(value)
            }

            "DIV" -> {
                if (stack.size < 2) return false

                val first = stack.removeLast()
                val second = stack.removeLast()

                if (first == 0L) return false

                val value = second / first

                stack.addLast(value)
            }

            "MOD" -> {
                if (stack.size < 2) return false

                val first = stack.removeLast()
                val second = stack.removeLast()

                if (first == 0L) return false

                val value = second % first

                stack.addLast(value)
            }

            "END" -> {
                return stack.size == 1
            }
        }

        return true
    }
}

class Command(val cmd: String) {
    var v = 0L
}