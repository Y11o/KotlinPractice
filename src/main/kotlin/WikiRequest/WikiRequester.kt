package WikiRequest

import com.google.gson.Gson

class WikiRequester(
    private val request: String
) {
    private var searchResults: List<Search> = listOf()
    fun createListOfSearches(){
        searchResults = Gson().fromJson(request, JsonComponents::class.java).query.search
        println("List of possible requests:")
        for (currSearch in searchResults.indices){
            println("${currSearch + 1}: ${searchResults[currSearch].title}")
        }
    }

    fun browse(){
        println("Input the number of request, you want to open in browser...")

    }
}