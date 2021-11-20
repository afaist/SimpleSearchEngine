fun main() {
    // put your code here
    var nDesk = 0
    for (i in 1..3) {
        val n = readLine()!!.toInt()
        nDesk += n / 2
        if (n % 2 != 0) nDesk += 1
    }
    println(nDesk)
}