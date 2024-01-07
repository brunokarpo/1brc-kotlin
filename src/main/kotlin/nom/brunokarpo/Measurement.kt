package nom.brunokarpo

data class Measurement(
    val station: String,
    val temperature: Double
) {

    companion object {
        fun fromLine(line: String): Measurement {
            val split = line.split(";")
            return Measurement(split[0], split[1].toDouble())
        }
    }

}

