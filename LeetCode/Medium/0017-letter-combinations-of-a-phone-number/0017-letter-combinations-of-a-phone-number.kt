class Solution {

    val letters: ArrayList<String> = arrayListOf()
    val pads: Array<String> = arrayOf(
        "abc",
        "def",
        "ghi",
        "jkl",
        "mno",
        "pqrs",
        "tuv",
        "wxyz"
    )

    fun letterCombinations(digits: String): List<String> {
        if (digits.isNotBlank()) {
            travel(digits, 0, "")
        }

        return letters
    }

    fun travel(target: String, start: Int, combination: String) {
        if (start == target.length) {
            letters += combination

            return
        }

        for (pad in pads[target[start] - '2']) {
            travel(target, start + 1, combination + pad)
        }
    }
}
