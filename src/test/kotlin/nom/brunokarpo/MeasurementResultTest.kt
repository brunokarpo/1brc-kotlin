package nom.brunokarpo

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MeasurementResultTest {
    @Test
    fun `should replace the min temperature from the result`() {
        val measurement = Measurement("station", -1.0)

        val measurementResult = MeasurementResult("station")
        measurementResult.addMeasurement(measurement)

        assertTrue(measurementResult.printResult().contains("=-1.0/"))
    }

    @Test
    fun `should keep the lower temperature as min`() {
        val measurement = Measurement("station", -1.0)
        val measurement2 = Measurement("station", 15.9)

        val measurementResult = MeasurementResult("station")
        measurementResult.addMeasurement(measurement)
        measurementResult.addMeasurement(measurement2)

        assertTrue(measurementResult.printResult().contains("=-1.0/"))
    }

    @Test
    fun `should get the first temperature as min temperature of measurement result`() {
        val measurement = Measurement("station", 19.0)

        val measurementResult = MeasurementResult("station")
        measurementResult.addMeasurement(measurement)

        assertTrue(measurementResult.printResult().contains("=19.0/"))
    }

    @Test
    fun `should replace the max temperature from the result`() {
        val measurement = Measurement("station", 19.0)

        val measurementResult = MeasurementResult("station")
        measurementResult.addMeasurement(measurement)

        assertTrue(measurementResult.printResult().endsWith("/19.0"))
    }

    @Test
    fun `should keep the greater temperature as max`() {
        val measurement = Measurement("station", 21.0)
        val measurement2 = Measurement("station", 12.9)

        val measurementResult = MeasurementResult("station")
        measurementResult.addMeasurement(measurement)
        measurementResult.addMeasurement(measurement2)

        assertTrue(measurementResult.printResult().endsWith("/21.0"))
    }

    @Test
    fun `should get the first temperature as max temperature of measurement result`() {
        val measurement = Measurement("station", -19.0)

        val measurementResult = MeasurementResult("station")
        measurementResult.addMeasurement(measurement)

        println(measurementResult.printResult())
        assertTrue(measurementResult.printResult().endsWith("/-19.0"))
    }

    @Test
    fun `should calculate the mean temperature`() {
        val measurement = Measurement("station", 21.0)
        val measurement2 = Measurement("station", 12.9)

        val measurementResult = MeasurementResult("station")
        measurementResult.addMeasurement(measurement)
        measurementResult.addMeasurement(measurement2)

        println(measurementResult.printResult())
        assertTrue(measurementResult.printResult().contains("/17.0/"))
    }
}
