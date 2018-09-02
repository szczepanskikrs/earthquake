package model.processing.validation

interface InputValidator {
    fun validate(coordinates: Pair<Double, Double>)
}