package model.parsing

interface InputParser {
    fun parse(input: String): Pair<Double, Double>
}