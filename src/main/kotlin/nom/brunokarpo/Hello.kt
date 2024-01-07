package nom.brunokarpo

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val fileReader = FileReader("/home/bruno/Documentos/measurements.txt")
    val channel = Channel<String>(1000)
    val measurementService = MeasurementService()

    launch {
        while (fileReader.readNextLine(channel)) {}
    }

    channel.consumeEach {
        val measurement = Measurement.fromLine(it)
        measurementService.addMeasurement(measurement)
    }

}
