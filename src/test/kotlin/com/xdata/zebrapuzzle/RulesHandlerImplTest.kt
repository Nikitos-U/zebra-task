package com.xdata.zebrapuzzle

import com.xdata.zebrapuzzle.dto.Attribute
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RulesHandlerImplTest {

    @Test
    fun isValidNoConstraintRules_exceptionThrown() {
        val rulesHandler = RulesHandlerImpl()
        val rules = RulesHelper.getRules(constraints = setOf())
        Assertions.assertThrows(
            Exception::class.java
        ) { rulesHandler.isValidRules(rules) }
    }

    @Test
    fun isValidZeroDomainsNumber_exceptionThrown() {
        val rulesHandler = RulesHandlerImpl()
        val rules = RulesHelper.getRules(domainsNumber = 0)
        Assertions.assertThrows(
            Exception::class.java
        ) { rulesHandler.isValidRules(rules) }
    }

    @Test
    fun isValidNonSquarePuzzle_exceptionThrown() {
        val rulesHandler = RulesHandlerImpl()
        val rules = RulesHelper.getRules(domainsNumber = 1)
        Assertions.assertThrows(
            Exception::class.java
        ) { rulesHandler.isValidRules(rules) }
    }

    @Test
    fun isValidNonEqualAttributesAndDomain_exceptionThrown() {
        val rulesHandler = RulesHandlerImpl()
        val rules = RulesHelper.getRules(domainsNumber = 2)
        Assertions.assertThrows(
            Exception::class.java
        ) { rulesHandler.isValidRules(rules) }
    }

    @Test
    fun getPossibleCombinations() {
        val rulesHandler = RulesHandlerImpl()
        val flatRules = RulesHelper.getRules(
            attributes = setOf(
                Attribute(type = "cigarettes", name = "Kools"),
                Attribute(type = "drinks", name = "water"),
                Attribute(type = "colors", name = "red")
            )
        )

        val flatCombinations = rulesHandler.getPossibleCombinations(flatRules)
        assertEquals(1, flatCombinations.size)
        val richRules = RulesHelper.getRules(
            attributes = setOf(
                Attribute(type = "cigarettes", name = "Kools"),
                Attribute(type = "drinks", name = "water"),
                Attribute(type = "colors", name = "red"),
                Attribute(type = "cigarettes", name = "Old gold"),
                Attribute(type = "drinks", name = "tea"),
                Attribute(type = "colors", name = "blue")
            )
        )

        val richCombinations = rulesHandler.getPossibleCombinations(richRules)
        assertEquals(4, richCombinations.size)
    }
}