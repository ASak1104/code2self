import java.util.TreeMap;
import java.util.StringTokenizer;

class Solution {

    val MAX_TIME = 24 * 60 - 1
    val totalTimes = TreeMap<Int, Int>()
    val inRecords = HashMap<Int, Int>()
    var basicPer = 0
    var basicFee = 0
    var extraPer = 0
    var extraFee = 0
    
    fun solution(fees: IntArray, records: Array<String>): IntArray {
        basicPer = fees[0]
        basicFee = fees[1]
        extraPer = fees[2]
        extraFee = fees[3]
        
        for (s in records) {
            val (time, number, info) = parse(s)
            
            if (info == "IN") {
                inRecords[number] = time
                continue
            }
            
            val diff = time - inRecords[number]!!
                
            totalTimes[number] = (totalTimes[number] ?: 0) + diff
            inRecords -= number
        }
        
        for ((number, time) in inRecords) {
            val diff = MAX_TIME - time
                
            totalTimes[number] = (totalTimes[number] ?: 0) + diff
        }
        
        val res = IntArray(totalTimes.size)
        var i = 0
        
        for (time in totalTimes.values) {
            res[i++] = calcFee(time)
        }
        
        return res
    }
    
    fun parse(s: String): Record {
        val st = StringTokenizer(s)
        val (h, m) = st.nextToken().split(':')
        val time = h.toInt() * 60 + m.toInt()
        val number = st.nextToken().toInt()
        val info = st.nextToken()
        
        return Record(time, number, info)
    }
    
    fun calcFee(totalTime: Int): Int {
        if (totalTime <= basicPer) {
            return basicFee
        }
        
        var ret = basicFee
        val diff = totalTime - basicPer
        
        ret += extraFee * (diff / extraPer)
        
        return if (diff % extraPer > 0) 
            ret + extraFee
        else 
            ret
    }
}

data class Record(val time: Int, val number: Int, val info: String)