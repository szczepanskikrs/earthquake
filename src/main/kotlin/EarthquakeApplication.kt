import model.communication.EarthQuakeResolver
import model.communication.EarthquakeService
import model.parsing.InputParser
import model.validation.InputValidator
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.util.function.Consumer

class EarthquakeApplication : KoinComponent {
    /**
     * Injection is done by Koin reader monad for purpose of lazy loading and lazy dependency instantiation.
     * For example exceptionHandler will never be created if exception will not be thrown.
     * Non constructor injection is design choice since it will not affect testing thanks to Koin.
     * For more details check ResolverFacadeTest.kt
     */
    private val retrofit by inject<EarthquakeService>()
    private val inputParser by inject<InputParser>()
    private val validator by inject<InputValidator>()
    private val exceptionHandler by inject<Consumer<Throwable>>()
    private val input by inject<String>()

    fun start() {
        println("Provide input in format longitude#latitude example -> -109#34")
        try {
            val parsedInput = inputParser.parse(input)
            validator.validate(parsedInput)
                    .let {
                        EarthQuakeResolver(retrofit, parsedInput).fetchData()
                    }
        } catch (e: Exception) {
            exceptionHandler.accept(e)
            System.exit(1)
        }
    }
}