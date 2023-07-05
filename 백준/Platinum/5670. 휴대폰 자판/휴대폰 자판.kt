import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val bw = System.out.bufferedWriter()
    var token = nextToken()

    while (token != StreamTokenizer.TT_EOF) {
        val n = nval.toInt()
        val words = Array(n) {
            nextToken()
            sval!!
        }

        val trie = Trie()

        words.forEach { trie.add(it) }

        var res = 0.0

        words.forEach { res += trie.count(it) }

        bw.append("%.2f\n".format(res / n))

        token = nextToken()
    }

    bw.flush()
    bw.close()
}

class Trie {
    val root = Node('?')

    init {
        root.end = true
    }

    fun add(word: String) {
        var node = root

        for (c in word) {
            if (!node.has(c)) {
                node.add(c)
            }

            node = node.get(c)
        }

        node.end = true
    }

    fun count(word: String): Int {
        var cnt = 0
        var node = root

        for (c in word) {
            if (node.end || node.counts > 1) cnt++

            node = node.get(c)
        }

        return cnt
    }

    data class Node(val w: Char) {
        val children = Array<Node?>(26) { null }
        var counts = 0
        var end = false

        fun has(c: Char) = children[c - 'a'] != null

        fun get(c: Char) = children[c - 'a']!!

        fun add(c: Char) {
            children[c - 'a'] = Node(c)
            counts++
        }
    }
}