const val MAX_KEY = 1_000_000

class MyHashMap() {

    val values = IntArray(MAX_KEY + 1) { -1 }

    fun put(key: Int, value: Int) {
        values[key] = value
    }

    fun get(key: Int): Int {
        return values[key]
    }

    fun remove(key: Int) {
        values[key] = -1
    }

}