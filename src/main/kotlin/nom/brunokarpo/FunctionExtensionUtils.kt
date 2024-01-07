package nom.brunokarpo

import java.math.BigDecimal
import java.math.RoundingMode

fun BigDecimal.toDefaultScale() : BigDecimal {
    return this.setScale(2, RoundingMode.DOWN)
}