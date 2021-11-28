package search

object Menu {
    private val menuItems = listOf(
        MenuItems.EXIT,
        MenuItems.FIND_PERSON,
        MenuItems.PRINT_ALL_PEOPLE
    )

    private fun printMenu() {
        println("\n=== Menu ===")
        println(menuItems.joinToString("\n") { it.item })
    }

    fun getMenuItemFromConsole(): MenuItems {
        while (true) {
            printMenu()
            try {
                val item = readLine()!!.toInt()
                if (item !in menuItems.indices) {
                    println("\nIncorrect option! Try again.")
                } else return menuItems[item]
            }catch (e: NumberFormatException){
                println("Incorrect input! Enter the number of the menu item.")
            }
        }
    }
}