package model.processing.parsing

import model.entities.ProcessedOutput
import model.entities.SingleQuake

interface OutputParser {
    fun parse(data: Pair<SingleQuake, Int>): ProcessedOutput
}