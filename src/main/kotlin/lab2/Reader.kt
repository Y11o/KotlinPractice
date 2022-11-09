package lab2

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.w3c.dom.NamedNodeMap
import org.w3c.dom.Node
import java.io.File
import java.io.FileInputStream
import javax.xml.parsers.DocumentBuilderFactory


class Reader {
   var hashMap: HashMap<Guide, Int> = hashMapOf() //карта, которая содержит адрес и количество повторений этого адреса

    fun readFromConsole(){
        var exit = false
        var path: String

        while(!exit){
            println ("Enter the path to the file.")
            println ("If you want to finish the work, click R")
            path = readLine().toString()
            if (path == "R" || path == "К") exit = true
            else {
                if (path.lowercase().endsWith(".csv"))
                    readCSV(path)
                else if (path.lowercase().endsWith(".xml"))
                    readXML(path)
                else throw Exception("Path entered incorrectly")
            }
        }
    }

    private fun readCSV(path: String){
        val startTime = System.currentTimeMillis()

        val inputStream = FileInputStream(path)
        val reader = inputStream.bufferedReader()
        val csvParser = CSVParser(reader, CSVFormat.DEFAULT
         //   .withDelimiter(';')
         //   .withQuote('"')
         //   .withRecordSeparator("\r\n")
         //   .withFirstRecordAsHeader()
         //   .withIgnoreHeaderCase()
         //   .withTrim()
        )

        for (csvRecord in csvParser) {
            val newGuide = Guide(
                csvRecord.get("city"),
                csvRecord.get("street"),
                csvRecord.get("house").toInt(),
                csvRecord.get("floor").toInt()
            )
            if (hashMap.isEmpty() || !hashMap.containsKey(newGuide)) //если не повторялась запись, то 1
                hashMap[newGuide] = 1
            else {
                hashMap[newGuide] = hashMap[newGuide]!! + 1 //если не 0, то добавляем единичку
            }
        }
        val runTime = System.currentTimeMillis() - startTime
        println("\nRuntime is $runTime milliseconds\n")

        printStatistic()
    }

    private fun readXML(path:String){
        val startTime = System.currentTimeMillis()

        val builderFactory = DocumentBuilderFactory.newInstance()
        val docBuilder = builderFactory.newDocumentBuilder()
        val doc = docBuilder.parse(File(path))
        val elements = doc.getElementsByTagName("address")

        for (index in 0 until elements.length){
            if(elements.item(0).nodeType == Node.ELEMENT_NODE){
                val attributes: NamedNodeMap = elements.item(index).attributes
                val newGuide = Guide(
                    attributes.getNamedItem("city").nodeValue,
                    attributes.getNamedItem("street").nodeValue,
                    attributes.getNamedItem("house").nodeValue.toInt(),
                    attributes.getNamedItem("floor").nodeValue.toInt()
                )
            }
        }
        val runTime = System.currentTimeMillis() - startTime
        println("\nRuntime is $runTime milliseconds\n")

        printStatistic()
    }

    fun printStatistic (){
        //дубликаты
        hashMap.forEach{
            println("${it.key} : кол-во повторов - ${it.value}")
        }

        //сколько в каждом городе домов n-го кол-ва этажей (1-5)
        val mapCity: HashMap<String, Pair<Int,Int>> = hashMapOf() //карта город - количество домов 1-5 этажей
        for ((key,value) in hashMap){
            if (!mapCity.containsKey(key.city)){
                mapCity[key.city] = Pair(key.floor, 1)
            }
            else{
                var count = 0
                when (key.floor){
                    1 -> {
                        count = mapCity.getValue(key.city).second + 1
                        mapCity[key.city] = Pair(1, count)
                    }
                    2 -> {
                        count = mapCity.getValue(key.city).second + 1
                        mapCity[key.city] = Pair(2, count)
                    }
                    3 -> {
                        count = mapCity.getValue(key.city).second + 1
                        mapCity[key.city] = Pair(3, count)
                    }
                    4 -> {
                        count = mapCity.getValue(key.city).second + 1
                        mapCity[key.city] = Pair(4, count)
                    }
                    5 -> {
                        count = mapCity.getValue(key.city).second + 1
                        mapCity[key.city] = Pair(5, count)
                    }
                }
            }
        }
    }

}


