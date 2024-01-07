package nom.brunokarpo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.withContext
import java.io.IOException
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Path

class FileReader(
    filename: String
) {
    private val reader = Files.newBufferedReader(Path.of(filename), Charset.defaultCharset())

    suspend fun readNextLine(channel: SendChannel<String>) : Boolean = withContext(Dispatchers.IO) {
        return@withContext try {
            channel.send(reader.readLine())
            true
        } catch (ex: IOException) {
            println("End of the file")
            false
        }
    }
}