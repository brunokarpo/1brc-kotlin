package nom.brunokarpo

import kotlinx.coroutines.coroutineScope

class MeasurementService {

    private val results = mutableListOf<MeasurementResult>()

    suspend fun addMeasurement(measurement: Measurement) = coroutineScope{
        println("Adding measurement")
        results.firstOrNull {
            it.station == measurement.station
        }?.addMeasurement(measurement) ?: kotlin.run {
            println("Creating new measurement result")
            val result = MeasurementResult(measurement.station)
            result.addMeasurement(measurement)
            results.add(result)
        }
    }

    suspend fun showResults() = coroutineScope{
        println(results)
    }

}