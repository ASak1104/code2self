class Solution {
    fun solution(numbers: LongArray): LongArray {
        return numbers.map { i -> maxTwoBitDiff(i) }.toLongArray()
    }

    fun maxTwoBitDiff(number: Long): Long {
        val lowBit = number.takeLowestOneBit()
        if (number < 3 || lowBit > 1) {
            return number + 1L
        }

        val numberBit = number.toString(2)
        if ("0" in numberBit) {
            return numberBit
                .reversed()
                .replaceFirst("10", "01")
                .reversed()
                .toLong(2);
        }
        return number + number.takeHighestOneBit();
    }
}