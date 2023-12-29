class Solution {
    
    val letters: ArrayList<String> = arrayListOf()
    val pads: Map<Char, String> = mapOf(
        '2' to "abc",
        '3' to "def",
        '4' to "ghi",
        '5' to "jkl",
        '6' to "mno",
        '7' to "pqrs",
        '8' to "tuv",
        '9' to "wxyz",
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

        for (pad in pads[target[start]]!!) {
            travel(target, start + 1, combination + pad)
        }
    }
}
