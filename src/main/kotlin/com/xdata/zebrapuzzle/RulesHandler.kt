package com.xdata.zebrapuzzle

import com.xdata.zebrapuzzle.dto.Attribute
import com.xdata.zebrapuzzle.dto.Rules

interface RulesHandler {
    fun isValidRules(rules: Rules?): Boolean

    fun getPossibleCombinations(rules: Rules): List<Set<Attribute>>
}