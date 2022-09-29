package wikiRequest

data class JsonComponents(
    val batchcomplete: String,
    val cContinue: Continue,
    val query: Query
)

data class Continue(
    val sroffset: Int,
    val cContinue: String
)

data class Query(
    val searchinfo: SearchInfo,
    val search: List<Search>
)

data class SearchInfo(
    val totalhits: Int
)

data class Search(
    val ns: Int,
    val title: String,
    val pageid: Int,
    val size: Int,
    val wordcount: Int,
    val snippet: String,
    val timestamp: String
)
