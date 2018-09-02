package model.processing.validation

import org.testng.annotations.DataProvider
import org.testng.annotations.Test

class DefaultValidatorTest {

    @Test(dataProvider = "correctValidationData")
    fun expectValidatorToCorrectlyValidateProvidedData(data: Pair<Double, Double>) {
        //given
        val validator = DefaultValidator()
        //when
        validator.validate(data)
        //then exception should not be thrown
    }

    @Test(dataProvider = "incorrectLongitudeData",
            expectedExceptions = [IllegalArgumentException::class],
            expectedExceptionsMessageRegExp = "Illegal latitude value can't be over 180 or less than -180")
    fun expectValidatorToThrowExceptionWhenValidatingIncorrectLongitudeData(data: Pair<Double, Double>) {
        //given
        val validator = DefaultValidator()
        //when
        validator.validate(data)
        //then IllegalArgumentException exception should be thrown with correct message
    }

    @Test(dataProvider = "incorrectLatitudeData",
            expectedExceptions = [IllegalArgumentException::class],
            expectedExceptionsMessageRegExp = "Illegal longitude value can't be over 90 or less than -90")
    fun expectValidatorToThrowExceptionWhenValidatingIncorrectLatitudeData(data: Pair<Double, Double>) {
        //given
        val validator = DefaultValidator()
        //when
        validator.validate(data)
        //then IllegalArgumentException exception should be thrown with correct message
    }

    @DataProvider
    fun correctValidationData(): Array<Pair<Double, Double>> {
        return arrayOf(
                Pair(1.0, 3.0),
                Pair(-39.0, -34.0),
                Pair(178.0, 89.0),
                Pair(-145.0, -43.0))
    }

    @DataProvider
    fun incorrectLongitudeData(): Array<Pair<Double, Double>> {
        return arrayOf(
                Pair(-231.0, 3.0),
                Pair(339.0, -34.0),
                Pair(2198.0, 89.0),
                Pair(-413445.0, -43.0))
    }

    @DataProvider
    fun incorrectLatitudeData(): Array<Pair<Double, Double>> {
        return arrayOf(
                Pair(1.0, -993.0),
                Pair(-39.0, -94.0),
                Pair(178.0, 99.0),
                Pair(-145.0, 123.0))
    }
}