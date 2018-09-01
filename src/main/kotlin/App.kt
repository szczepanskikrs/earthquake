import di.*
import kotlinx.coroutines.experimental.runBlocking
import org.koin.log.EmptyLogger
import org.koin.standalone.StandAloneContext.startKoin

fun main(args: Array<String>) = runBlocking {
    startKoin(listOf(
            retrofitModule,
            validatorModule,
            exceptionHandlerModule,
            inputModule,
            parserModule),
            logger = EmptyLogger())

    EarthquakeApplication()
            .start()
}