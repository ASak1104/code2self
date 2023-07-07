class Solution {
    fun solution(words: Array<String>, queries: Array<String>): IntArray {
        val tries = mutableMapOf<Int, Trie>()
        val revTries = mutableMapOf<Int, Trie>()

        for (word in words) {
            if (word.length !in tries) {
                tries[word.length] = Trie()
                revTries[word.length] = Trie()
            }

            tries[word.length]!!.addWord(word)
            revTries[word.length]!!.addWord(word.reversed())
        }

        val answer = IntArray(queries.size) {
            val query = queries[it]

            if (query.first() == '?' && query.last() == '?') {
                tries[query.length]?.size ?: 0
            }

            if (query.last() == '?') {
                tries[query.length]?.match(query) ?: 0
            } else {
                revTries[query.length]?.match(query.reversed()) ?: 0
            }
        }

        return answer
    }
}

class Trie {
    val root = Node('/')
    var size = 0

    fun addWord(word: String) {
        var node = root

        for (c in word) {
            if (!node.has(c)) {
                node.add(c)
            }

            node.counts++
            node = node.next(c)
        }
        size++
    }

    fun match(word: String) = match(root, word, 0)

    fun match(node: Node, word: String, i: Int): Int {
        if (i == word.length) return node.counts

        if (word[i] != '?') {
            return if (node.has(word[i]))
                match(node.next(word[i]), word, i + 1)
            else
                0
        }

        return node.counts
    }

    class Node(val c: Char) {
        val nexts = Array<Node?>(26) { null }
        var counts = 0

        fun has(c: Char) = nexts[c - 'a'] != null

        fun next(c: Char) = nexts[c - 'a']!!

        fun add(c: Char) {
            nexts[c - 'a'] = Node(c)
        }
    }
}