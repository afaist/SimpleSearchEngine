package search

import java.io.File

class DB {
    private val df = mutableListOf<String>()
    private var mapIndexed = mutableMapOf<String, MutableSet<Int>>()
    private var searchType = SearchType.ALL

    fun setSearchType(value: String) {
        searchType = when (value) {
            "ANY" -> SearchType.ANY
            "ALL" -> SearchType.ALL
            "NONE" -> SearchType.NONE
            else -> {
                println("Incorrect search strategy, used All")
                SearchType.ALL
            }
        }
    }

    /**
     * Load data from file
     *
     * @param filename
     * @return - result loading
     */
    fun loadFromFile(filename: String): Boolean {
        val file = File(filename)
        return if (file.exists()) {
            df.clear()
            file.forEachLine {
                df.add(it)
            }
            for (i in df.indices) {
                for (word in df[i].split("\\s+".toRegex())) {
                    mapIndexed[word.lowercase()]?.add(i) ?: mapIndexed.put(word.lowercase(), mutableSetOf(i))
                }
            }

            true
        } else {
            println("File $filename not found!!")
            false
        }
    }


    /**
     * Find string in data
     *
     * @param string
     */
    fun search(string: String) {
        val key = string.lowercase()
        var resultSet = setOf<Int>()
        for (word in key.split(" ")) {
            val tmpSet = mapIndexed[word.lowercase()]
            if (tmpSet != null) {
                resultSet = if (resultSet.isEmpty()) {
                    resultSet.union(tmpSet)
                } else {
                    when (searchType) {
                        SearchType.ALL -> resultSet.intersect(tmpSet)
                        SearchType.ANY -> resultSet.union(tmpSet)
                        SearchType.NONE -> resultSet.union(tmpSet)
                    }
                }
            }
        }
        if (searchType == SearchType.NONE) {
            val allSet = (0 until df.size).toSet()
            resultSet = allSet.subtract(resultSet)
        }
        if (resultSet.isEmpty()) {
            println("No matching people found.")
        } else {
            val list = resultSet.toList().sorted()
            println("${list.size} person${if (list.size > 1) "s" else ""} found:")
            for (i in list) {
                println(df[i])
            }
        }
    }

    /**
     * Print all list of people
     *
     */
    fun printAllData() {
        println("\n=== List of people ===")
        println(df.joinToString("\n"))
    }

}