package WikiRequest

fun main() {
    val request = ReaderFromConsole()
    val wikiReq = WikiRequester(request.getRequest())
}