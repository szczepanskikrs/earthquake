package di

import CommonValues
import CommonValues.EXCEPTION_HANDLER
import CommonValues.OUTPUT_HANDLER
import model.processing.validation.DefaultValidator
import model.communication.EarthquakeService
import model.processing.parsing.DefaultInputParser
import model.processing.parsing.InputParser
import model.processing.validation.InputValidator
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.function.Consumer

/**
 * Dependency injection configuration.
 */

val retrofitModule = module {
    single(CommonValues.RETROFIT) {
        Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(CommonValues.BASE_URL)
                .build()
                .create(EarthquakeService::class.java)
    }
}

val validatorModule = module {
    single<InputValidator> { DefaultValidator() }
}

val parserModule = module {
    factory<InputParser> { DefaultInputParser() }
}

val exceptionHandlerModule = module {
    single(EXCEPTION_HANDLER) { Consumer<Throwable>(System.out::println) }
}

val inputModule = module {
    factory { readLine()!! }
}

val outputModule = module {
    single(OUTPUT_HANDLER) { Consumer<String>(System.out::println) }
}