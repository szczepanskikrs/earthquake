package model.validation

class DefaultValidator : InputValidator {
    override fun validate(coordinates: Pair<Double, Double>) {
        when {
            coordinates.first > 180 || coordinates.first < -180 -> throw IllegalArgumentException("Illegal latitude value can't be over 180 or less than -180")
            coordinates.second > 90 || coordinates.second < -90 -> throw IllegalArgumentException("Illegal longitude value can't be over 90 or less than -90")
        }
    }
}