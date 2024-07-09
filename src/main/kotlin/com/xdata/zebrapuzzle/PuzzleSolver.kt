package com.xdata.zebrapuzzle

import com.xdata.zebrapuzzle.dto.Attribute
import com.xdata.zebrapuzzle.dto.Domain
import com.xdata.zebrapuzzle.dto.Rules

interface PuzzleSolver {
    fun solvePuzzle(
        rules: Rules, possibleCombinations: List<Set<Attribute>>
    ): List<Domain>?
}