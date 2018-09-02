import CommonValues.EXCEPTION_HANDLER
import CommonValues.OUTPUT_HANDLER
import model.communication.DefaultQuakeProvider
import model.entities.SingleQuake
import model.processing.computation.DefaultDistanceCalculator
import model.processing.parsing.DefaultOutputParser
import model.processing.parsing.InputParser
import model.processing.validation.InputValidator
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.util.function.Consumer

class EarthquakeApplication : KoinComponent {
    /**
     * Injection is done by Koin reader monad for purpose of lazy loading and lazy dependency instantiation.
     * For example exceptionHandler will never be created if exception will not be thrown.
     * Non constructor injection is design choice since it will not affect testing thanks to Koin.
     */
    private val inputParser by inject<InputParser>()
    private val validator by inject<InputValidator>()
    private val exceptionHandler by inject<Consumer<Throwable>>(EXCEPTION_HANDLER)
    private val input by inject<String>()
    private val output by inject<Consumer<String>>(OUTPUT_HANDLER)

    fun start() {
        output.accept("Provide input in format longitude#latitude example -> -109#34")
        var parsedInput: Pair<Double, Double>? = null
        try {
            parsedInput = inputParser.parse(input)
            validator.validate(parsedInput)
        } catch (e: Exception) {
            exceptionHandler.accept(e)
            System.exit(1)
        }
        output.accept("Fetching data please wait")
        parsedInput?.let {
            DefaultQuakeProvider(it).fetchData()?.let { receivedData ->
                receivedData.map { quake -> Pair(quake, DefaultDistanceCalculator().calculateDistance(quake, parsedInput)) }
                        .sortedBy(Pair<SingleQuake, Int>::second)
                        .distinctBy { (first) -> first.geometry }
                        .take(10)
                        .map(DefaultOutputParser()::parse)
                        .forEach { processed -> output.accept(processed.toString()) }
            }
        }
    }
}