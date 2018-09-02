package model.processing.parsing

interface InputParser {
    fun parse(input: String): Pair<Double, Double>
}