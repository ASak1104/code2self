import java.util.*

val easyProblems = PriorityQueue<Problem>()
val hardProblems = PriorityQueue<Problem>(reverseOrder())
val solvedProblems = mutableSetOf<Problem>()
val levelCache = mutableMapOf<Int, Int>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()
    val n = readLine().toInt()

    repeat(n) {
        val problem = StringTokenizer(readLine()).run { Problem(nextToken().toInt(), nextToken().toInt()) }

        hardProblems += problem
        easyProblems += problem
        levelCache[problem.number] = problem.level
    }

    val m = readLine().toInt()

    repeat(m) {
        val st = StringTokenizer(readLine())
        val nextInt = { st.nextToken().toInt() }
        val cmd = st.nextToken()

        when (cmd) {
            "add" -> add(nextInt(), nextInt())

            "solved" -> solved(nextInt())

            else -> sb.append(recommend(nextInt())).appendLine()
        }
    }

    println(sb)
    close()
}

fun recommend(x: Int): Int {
    val problems = if (x > 0) {
        hardProblems
    } else {
        easyProblems
    }

    while (problems.peek() in solvedProblems) {
        problems.poll()
    }

    return problems.peek().number
}

fun add(number: Int, level: Int) {
    val problem = Problem(number, level)

    hardProblems += problem
    easyProblems += problem
    levelCache[number] = level
}

fun solved(number: Int) {
    solvedProblems += Problem(number, levelCache[number]!!)
}

data class Problem(val number: Int, val level: Int) : Comparable<Problem> {

    override fun compareTo(other: Problem): Int {
        return if (level == other.level)
            number - other.number
        else
            level - other.level
    }
}
