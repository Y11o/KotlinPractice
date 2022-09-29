package WikiRequest

import java.net.HttpURLConnection
import java.net.URL

class ReaderFromConsole {
    private var userRequest: String = ""
    fun getRequest(): String {
        while (userRequest == "") {
            println("Enter your Wikipedia request...")
            userRequest = readln()
        }
        val connect =
            URL("https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch=$userRequest").openConnection() as HttpURLConnection
        return connect.inputStream.bufferedReader().readText()
    }
}