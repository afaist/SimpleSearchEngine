package search

fun main() {
    val str = readLine()!!.split(" ")
    val word = readLine()!!
    val pos = str.indexOf(word)
    println(if (pos == -1) "Not found" else pos + 1)
}
