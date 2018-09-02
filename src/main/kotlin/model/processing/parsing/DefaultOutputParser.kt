package model.processing.parsing

import model.entities.ProcessedOutput
import model.entities.SingleQuake

class DefaultOutputParser : OutputParser {
    override fun parse(data: Pair<SingleQuake, Int>): ProcessedOutput {
        return ProcessedOutput(
                title = data.first.details.title,
                distanceFromEpicenter = data.second)
    }
}