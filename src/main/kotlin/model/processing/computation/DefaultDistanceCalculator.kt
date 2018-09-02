package model.processing.computation

import model.entities.SingleQuake

class DefaultDistanceCalculator : DistanceCalculator {
    private val defaultPower = 2.0
    private val defaultRate = 1.0
    override fun calculateDistance(earthQuake: SingleQuake, coordinates: Pair<Double, Double>): Int {
        val pointLatitude = coordinates.first
        val quakeLatitude = earthQuake.geometry.coordinates.first()
        val pointLongitude = coordinates.second
        val quakeLongitude = earthQuake.geometry.coordinates.last()
        return distanceBetween(pointLatitude, quakeLatitude, pointLongitude, quakeLongitude)
    }

    private fun distanceBetween(pointLatitude: Double, quakeLatitude: Double, pointLongitude: Double, quakeLongitude: Double): Int {
        return Math.sqrt(Math.pow(pointLatitude - quakeLatitude, defaultPower) +
                Math.pow(pointLongitude - quakeLongitude, defaultPower) * defaultRate).toInt()
    }
}