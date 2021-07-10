package homework.hard

import homework.hard.dto.Dictionary
import kotlinx.coroutines.*
import java.io.File

fun main() {
    val start = System.currentTimeMillis()
    val dictionaryApi = DictionaryApi()
    val words = FileReader.readFile().trimEnd().split(" ", "\n").toSet()

    val dictionaries = runBlocking {
        return@runBlocking findWords(dictionaryApi, words, Locale.EN)
    }

    dictionaries.map { dictionary ->
        print("For word ${dictionary.word} i found examples: ")
        println(dictionary.meanings.map { definition -> definition.definitions.map { it.example } })
    }

    val finish = System.currentTimeMillis()
    println("result -> ${finish - start} ms.")
}

suspend fun findWords(dictionaryApi: DictionaryApi, words: Set<String>, locale: Locale): List<Dictionary> = coroutineScope {
    return@coroutineScope words.map {
        async {
            println("started coroutineScope")
            dictionaryApi.findWord(locale, it)
        }
    }
    .awaitAll()
    .toList()
}

object FileReader {
    fun readFile(): String =
        File(
            this::class.java.classLoader.getResource("words.txt")?.toURI() ?: throw RuntimeException("Can't read file")
        ).readText()
}
