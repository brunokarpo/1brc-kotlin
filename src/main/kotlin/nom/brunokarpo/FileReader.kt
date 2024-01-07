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
        println("Reading next line of file")
        return@withContext try {
            reader.readLine()?.let {
                channel.send(it)
                return@let true
            } ?: kotlin.run {
                println("Last line of file read. Closing channel to send")
                channel.close()
                false
            }
        } catch (ex: IOException) {
            println("End of the file. Closing channel to send")
            channel.close(ex)
            false
        }
    }
}