import java.util.*
import kotlin.math.abs

class Solution {
    val orders = ArrayList<String>(6)

    fun solution(expression: String): Long {
        val opeList = ArrayList<Char>(49)
        val numList = ArrayList<Int>(50)
        val sb = StringBuilder()

        for (c in expression) {
            if (c.isDigit()) {
                sb.append(c)
            } else {
                opeList += c
                numList += sb.toString().toInt()
                sb.clear()
            }
        }

        numList += sb.toString().toInt()

        setOrders(opeList.distinct())

        var res = 0L

        for (order in orders) {
            res = maxOf(res, abs(calc(opeList, numList, order)))
        }

        return res
    }

    fun setOrders(opes: List<Char>) {
        val end = (1 shl opes.size) - 1

        fun travel(order: String, visit: Int) {
            if (visit == end) {
                orders += order
                return
            }

            for (i in opes.indices) {
                val mask = 1 shl i

                if (visit and mask == mask) continue
                
                travel(order + opes[i], visit or mask)
            }
        }

        for (i in opes.indices) {
            travel(opes[i].toString(), 1 shl i)
        }
    }

    fun calc(opeList: List<Char>, numList: List<Int>, order: String): Long {
        val opes = LinkedList(opeList)
        val nums = LinkedList(numList.map { it.toLong() })

        for (target in order) {
            var i = 0

            while (i < opes.size) {
                if (opes[i] != target) {
                    i++
                    continue
                }

                val v = operate(nums[i], nums[i + 1], opes[i])

                opes.removeAt(i)
                nums.removeAt(i)
                nums[i] = v
            }
        }

        return nums[0]
    }

    fun operate(a: Long, b: Long, ope: Char): Long {
        return when (ope) {
            '+' -> a + b
            '-' -> a - b
            else -> a * b
        }
    }
}