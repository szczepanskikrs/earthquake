package model.parsing

class DefaultInputParser : InputParser {
    override fun parse(input: String): Pair<Double, Double> {
        return try {
            input.split("#")
                    .map(String::toDouble)
                    .chunked(2) { (lat, lon) -> Pair(lat, lon) }
                    .first()
        } catch (e: Exception) {
            throw IllegalArgumentException("Could not parse input try longitude#latitude example -14#24, only numeric values accepted")
        }
    }
}