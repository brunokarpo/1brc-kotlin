package nom.brunokarpo

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun main() = runBlocking {
    val startTime = Instant.now().toEpochMilli()
    val fileReader = FileReader("./measurements.txt")
    val channel = Channel<String>(100)
    val measurementService = MeasurementService()

    launch {
        while (fileReader.readNextLine(channel)) {}
    }

    channel.consumeEach {
        println("Consuming measurement from channel")
        val measurement = Measurement.fromLine(it)
        measurementService.addMeasurement(measurement)
    }

    measurementService.showResults()

    val endTime = Instant.now().toEpochMilli()

    println("Total time = ${endTime - startTime}")
}
