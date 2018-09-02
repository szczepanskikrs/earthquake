package model.communication

import model.entities.SingleQuake

interface QuakeProvider {
    fun fetchData(): List<SingleQuake>?
}