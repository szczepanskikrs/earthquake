package model.communication

import model.entities.SingleQuake

class EarthQuakeResolver(private val retrofit: EarthquakeService, private val coordinates: Pair<Double, Double>) {
    fun fetchData() {
        retrofit.getEarthQuakes(longitude = coordinates.first.toInt(), latitude = coordinates.second.toInt())
                .execute()
                .body()?.quakes?.forEach(System.out::println)
    }

    fun mapToOutput(quake: SingleQuake): Output {
        return Output(name = quake.details.title)
    }
}

data class Output(val name: String?)