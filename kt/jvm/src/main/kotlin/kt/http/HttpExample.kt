package kt.http

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException


class HttpExample {

    private val client = OkHttpClient()

    @Throws(IOException::class)
    fun run(url: String): String? {
        val request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).execute().use { response -> return response.body?.string() }
//        // readLines by use impl auto try catch finally
//        File("").readLines().forEach { println(it) }
    }
}

fun main() {
    val httpClient = HttpExample()
    val response = httpClient.run("https://raw.github.com/square/okhttp/master/README.md")
    println(response)
}