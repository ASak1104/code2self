import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    fun readString(): String {
        nextToken()
        return sval!!
    }

    val c = readInt()
    val n = readInt()
    val legend = Legend()

    repeat(c) {
        legend.addColor(readString())
    }

    repeat(n) {
        legend.addName(readString())
    }

    val q = readInt()
    val bw = System.out.bufferedWriter()

    repeat(q) {
        bw.append(if(legend.query(readString())) "Yes\n" else "No\n")
    }

    bw.flush()
    bw.close()
}

class Legend {
    val colorTrie = Trie()
    val nameSet = HashSet<String>()
    var maxName = 0
    var minName = 1001

    fun query(teamName: String): Boolean {
        var node = colorTrie.root

        for (i in teamName.indices) {
            val size = teamName.length - i - 1

            if (size < minName || !node.has(teamName[i])) {
                return false
            }

            node = node.get(teamName[i])!!

            if (node.end && size <= maxName) {
                if (teamName.substring(i + 1) in nameSet) {
                    return true
                }
            }
        }

        return false
    }

    fun addColor(word: String) {
        colorTrie.add(word)
    }

    fun addName(word: String) {
        nameSet.add(word)
        minName = minOf(minName, word.length)
        maxName = maxOf(maxName, word.length)
    }
}

class Trie {
    val root = Node()

    fun add(word: String): Node {
        var node = root

        for (c in word) {
            if (!node.has(c)) {
                node.add(c)
            }

            node = node.get(c)!!
        }

        node.end = true

        return node
    }

    class Node {
        val child = Array<Node?>(26) { null }
        var end = false

        fun has(c: Char) = child[c - 'a'] != null

        fun add(c: Char) {
            child[c - 'a'] = Node()
        }

        fun get(c: Char) = child[c - 'a']
    }
}