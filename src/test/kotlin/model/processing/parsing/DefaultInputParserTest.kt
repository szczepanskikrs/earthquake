package model.processing.parsing

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

class DefaultInputParserTest {

    @Test(dataProvider = "provideCorrectData")
    fun expectParserToCorrectlyParseProvidedStrings(correctData: InputParserTestData) {
        //given
        val parser = DefaultInputParser()
        //when
        val parsedOutput = parser.parse(correctData.input)
        //then
        assertThat(parsedOutput).isEqualTo(correctData.parsedOutput)
    }

    @Test(dataProvider = "provideIncorrectData",
            expectedExceptions = [IllegalArgumentException::class],
            expectedExceptionsMessageRegExp = "Could not parse input try longitude#latitude example -14#24, only numeric values accepted")
    fun expectParserToThrowExceptionWhenParsingIncorrectString(incorrectData: InputParserTestData) {
        //given
        val parser = DefaultInputParser()
        //when
        parser.parse(incorrectData.input)
        //then IllegalArgumentException should be thrown.
    }

    @DataProvider
    fun provideCorrectData(): Array<InputParserTestData> {
        return arrayOf(
                InputParserTestData("3#-59", Pair(3.0, -59.0)),
                InputParserTestData("189#34", Pair(189.0, 34.0)),
                InputParserTestData("-98#78", Pair(-98.0, 78.0)),
                InputParserTestData("190#90", Pair(190.0, 90.0))
        )
    }

    @DataProvider
    fun provideIncorrectData(): Array<InputParserTestData> {
        return arrayOf(
                InputParserTestData("-3$-59", Pair(3.0, -59.0)),
                InputParserTestData("pies", Pair(189.0, 34.0)),
                InputParserTestData("-51taegae8", Pair(-98.0, 78.0)),
                InputParserTestData("DeusVult", Pair(190.0, 90.0))
        )
    }

    data class InputParserTestData(val input: String, val parsedOutput: Pair<Double, Double>)
}