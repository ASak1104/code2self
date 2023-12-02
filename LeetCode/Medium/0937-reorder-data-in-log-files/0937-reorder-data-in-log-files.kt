class Solution {

    val letterLogs = ArrayList<String>()
    val digitLogs = ArrayList<String>()

    fun reorderLogFiles(logs: Array<String>): Array<String> {
        for (log in logs) {
            if (log.last().isDigit()) {
                digitLogs += log
            } else {
                letterLogs += log
            }
        }

        letterLogs.sortWith(this::compareLetterLogs)

        return (letterLogs + digitLogs).toTypedArray()
    }

    fun compareLetterLogs(a: String, b: String): Int {
        val aSplit = a.split(' ')
        val bSplit = b.split(' ')
        var aIdx = 1
        var bIdx = 1

        while (aIdx < aSplit.size && bIdx < bSplit.size) {
            if (aSplit[aIdx] != bSplit[bIdx]) {
                return aSplit[aIdx].compareTo(bSplit[bIdx])
            }

            aIdx++
            bIdx++
        }

        if (aSplit.size == bSplit.size) {
            return aSplit[0].compareTo(bSplit[0])
        }

        return aSplit.size.compareTo(bSplit.size)
    }
}
