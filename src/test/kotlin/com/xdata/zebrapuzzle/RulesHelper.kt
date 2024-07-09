package com.xdata.zebrapuzzle

import com.xdata.zebrapuzzle.dto.Attribute
import com.xdata.zebrapuzzle.dto.Constraint
import com.xdata.zebrapuzzle.dto.Operator
import com.xdata.zebrapuzzle.dto.Rules

object RulesHelper {
    fun getRules(
        domainsNumber: Int = 5,
        constraints: Set<Constraint> = getConstraints(),
        attributes: Set<Attribute> = getAttributes(),
    ): Rules = Rules(
        domainsNumber = domainsNumber, constraints = constraints, attributes = attributes
    )

    private fun getConstraints(): Set<Constraint> = setOf(
        Constraint(
            attributes = getAttributes(), operator = Operator.EQUALITY
        )
    )

    private fun getAttributes(): Set<Attribute> = setOf(
        Attribute(
            type = "drinks", name = "water"
        ), Attribute(
            type = "colors", name = "red"
        )
    )
}