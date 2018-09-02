package model.communication

import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class DefaultQuakeProvider(private val coordinates: Pair<Double, Double>) : QuakeProvider, KoinComponent {
    private val retrofit by inject<EarthquakeService>()

    override fun fetchData() = retrofit.getEarthQuakes(
            longitude = coordinates.first.toInt(),
            latitude = coordinates.second.toInt())
            .execute()
            .body()?.quakes
}