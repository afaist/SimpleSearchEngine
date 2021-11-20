const val FLIGHTS = 2
fun main() = readLine()!!.toInt().run {
    println(readLine()!!.toInt() * this + readLine()!!.toInt() * FLIGHTS + readLine()!!.toInt() * (this - 1))
}