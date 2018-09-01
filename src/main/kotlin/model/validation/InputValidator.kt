package model.validation

interface InputValidator {
    fun validate(coordinates: Pair<Double, Double>)
}