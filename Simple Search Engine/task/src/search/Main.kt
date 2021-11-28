package search

fun main(args: Array<String>) {
    var fileName: String? = null
    for (i in args.indices) {
        if (args[i] == "--data") {
            try {
                fileName = args[i + 1]
                break
            } catch (e: IndexOutOfBoundsException) {
                println("Wrong parameters!!\nYou must specify the file name in the command line as --data file")
                return
            }
        }
    }
    if (fileName == null) {
        println("File name is not specified on the command line")
        println("You must specify the file name in the command line as --data file")
        return
    }
    val db = DB()
    if (!db.loadFromFile(fileName)) {
        return
    }
    val menu = Menu
    do {
        val items = menu.getMenuItemFromConsole()
        when (items) {
            MenuItems.PRINT_ALL_PEOPLE -> db.printAllData()
            MenuItems.FIND_PERSON -> {
                println("Select a matching strategy: ALL, ANY, NONE")
                db.setSearchType(readLine()!!.uppercase())
                println("Enter a name or email to search all suitable people.")
                val value = readLine()!!
                db.search(value)
            }
            MenuItems.EXIT -> break
        }
    } while (items != MenuItems.EXIT)
    println("Bye!")
}

