fun bill(priceList: Map<String, Int>, shoppingList: MutableList<String>) = shoppingList.sumOf { priceList[it] ?: 0 }
