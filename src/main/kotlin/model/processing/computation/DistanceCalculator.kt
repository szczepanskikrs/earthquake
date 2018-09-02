package model.processing.computation

import model.entities.SingleQuake

interface DistanceCalculator {
    fun calculateDistance(earthQuake: SingleQuake, coordinates: Pair<Double, Double>):Int
}