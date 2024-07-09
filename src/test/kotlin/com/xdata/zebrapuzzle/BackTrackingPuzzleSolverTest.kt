package com.xdata.zebrapuzzle

import com.xdata.zebrapuzzle.dto.Attribute
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BackTrackingPuzzleSolverTest {

    @Test
    fun solvePuzzleZeroCombinations_null() {
        val puzzleSolver = BackTrackingPuzzleSolver()
        val domains = puzzleSolver.solvePuzzle(RulesHelper.getRules(), listOf())
        assertEquals(null, domains)
    }

    @Test
    fun solvePuzzle_happyCase() {
        val filePath = "src/test/resources/good-rules.json"
        val fileHandler = FileHandlerImpl()
        val rulesHandler = RulesHandlerImpl()
        val puzzleSolver = BackTrackingPuzzleSolver()

        val rules = fileHandler.readRulesFromJson(filePath)

        val domains = puzzleSolver.solvePuzzle(rules, rulesHandler.getPossibleCombinations(rules))
        assertEquals(5, domains!!.size)
        assertEquals(5, domains.map { it.attributesSet }.size)
        assertEquals("water", domains.first {
            it.attributesSet.contains(
                Attribute(
                    type = "nationalities", name = "Norwegian"
                )
            )
        }.attributesSet.first { it.type == "drinks" }.name)
        assertEquals("zebra", domains.first {
            it.attributesSet.contains(
                Attribute(
                    type = "nationalities", name = "Japanese"
                )
            )
        }.attributesSet.first { it.type == "pets" }.name)

    }
}