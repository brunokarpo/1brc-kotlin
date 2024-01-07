package nom.brunokarpo

import java.util.Locale

class MeasurementResult(
    val station: String
) {
    private var min: Double = 0.0
    private var max: Double = 0.0
    private var mean: Double = 0.0

    private var counter: Int = 0

    fun addMeasurement(measurement: Measurement) {
        min = if(counter == 0 || measurement.temperature < min) measurement.temperature else min
        max = if(counter == 0 || measurement.temperature > max) measurement.temperature else max

        mean = ((mean * counter) + measurement.temperature) / (counter + 1)
        counter++
    }

    override fun toString(): String {
        return String.format(Locale.ENGLISH, "%s=%.1f/%.1f/%.1f", station, min, mean, max)
    }
}
