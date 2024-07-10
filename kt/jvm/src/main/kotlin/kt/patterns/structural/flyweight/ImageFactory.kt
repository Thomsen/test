package kt.patterns.structural.flyweight

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.io.ByteArrayOutputStream
import java.net.URL

data class Image(val bytes: ByteArray)

class ImageFactory {
    private val cache = mutableMapOf<String, Image>()
    private val locks = mutableMapOf<String, Mutex>()
    private val mutex = Mutex()

    suspend fun get0(url: String): Image? {
        val cachedImage = cache[url]
        return if (cachedImage == null) {
            // Replace with your fetching method
            val fetchedImage = fetchImage(url)
            if (fetchedImage != null) {
                cache[url] = fetchedImage
            }
            fetchedImage
        } else {
            cachedImage
        }
    }

    suspend fun get(url: String): Image? {
        mutex.lock()
        val existingMutex = locks[url]
        if (existingMutex != null) {
            mutex.unlock()
            existingMutex.withLock {
                return getLocked(url)
            }
        } else {
            val newMutex = Mutex()
            locks[url] = newMutex
            mutex.unlock()
            newMutex.withLock {
                return getLocked(url)
            }
        }
    }

    private suspend fun getLocked(url: String): Image? {
        val cachedImage = cache[url]
        return if (cachedImage == null) {
            // Replace with your fetching method
            val fetchedImage = fetchImage(url)
            if (fetchedImage != null) {
                cache[url] = fetchedImage
                locks.remove(url)
            }
            fetchedImage
        } else {
            locks.remove(url)
            cachedImage
        }
    }

    private suspend fun fetchImage(url: String): Image? {
        try {
            val imageUrl = URL(url)
            val conn = imageUrl.openConnection()
            conn.connect()

            val inputStream = conn.getInputStream()
            //val outputPath = "${Calendar.getInstance().timeInMillis}.jpg"
            //val outputStream = FileOutputStream(outputPath)
            val outputStream = ByteArrayOutputStream()

            val buffer = ByteArray(4096)
            var bytesRead: Int

            while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                outputStream.write(buffer, 0, bytesRead)
            }

//            outputStream.close()
            inputStream.close()

            println("Image downloaded successfully to")

            return Image(outputStream.toByteArray());

        } catch (e: Exception) {
            println("Error downloading image: ${e.message}")
        }
        return null
    }
}