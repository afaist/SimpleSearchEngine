fun main() {
    // put your code here
    val first = IntArray(3) { readLine()!!.toInt() }
    val second = IntArray(3) { readLine()!!.toInt() }
    println(((second[0] - first[0]) * 60 + second[1] - first[1]) * 60 + second[2] - first[2])
}