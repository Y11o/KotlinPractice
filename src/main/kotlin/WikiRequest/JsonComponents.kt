package WikiRequest

data class JsonComponents(
    val batchComplete: String,
    val cContinue: Continue,
    val query: Query
)

data class Continue(
    val srOffset: Int,
    val cContinue: String
)

data class Query(
    val searchInfo: SearchInfo,
    val search: List<Search>
)

data class SearchInfo(
    val totalHits: Int
)

data class Search(
    val ns: Int,
    val title: String,
    val pageId: Int,
    val size: Int,
    val wordCount: Int,
    val snippet: String,
    val timestamp: String
)
