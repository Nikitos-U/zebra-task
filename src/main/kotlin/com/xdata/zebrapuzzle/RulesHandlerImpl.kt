package com.xdata.zebrapuzzle

import com.xdata.zebrapuzzle.dto.Attribute
import com.xdata.zebrapuzzle.dto.Constraint
import com.xdata.zebrapuzzle.dto.Operator
import com.xdata.zebrapuzzle.dto.Rules

class RulesHandlerImpl : RulesHandler {
    override fun isValidRules(rules: Rules?): Boolean {
        return when {
            rules == null -> throw Exception("Rules must not be null")
            rules.constraints.isEmpty() -> throw Exception("Rules must contain at least one constraint")
            rules.domainsNumber == 0 -> throw Exception("Domains number should be greater then 0")
            getAllAttributesFromRules(rules).groupBy { it.type }.map { it.value.size }
                .any { size -> size != rules.domainsNumber } -> throw Exception("Number of rules of each type should be equal to domains number")

            rules.domainsNumber * rules.domainsNumber != (getAllAttributesFromRules(rules).size) -> throw Exception(
                "Non square puzzle domain"
            )

            else -> true
        }
    }

    override fun getPossibleCombinations(rules: Rules): List<Set<Attribute>> {
        val groupedAttributes =
            getAllAttributesFromRules(rules).groupBy { it.type }.mapValues { it.value.toSet() }.values.toList()
        return filterCombinations(rules.constraints, findCombinationsRecursively(groupedAttributes))
    }

    private fun getAllAttributesFromRules(rules: Rules): List<Attribute> {
        val attributes = rules.constraints.flatMap { it.attributes }.distinct().toMutableSet()
        attributes.addAll(rules.attributes)
        return attributes.distinct()
    }

    private fun findCombinationsRecursively(lists: List<Set<Attribute>>): List<Set<Attribute>> {
        if (lists.isEmpty()) return listOf(emptySet())

        val result = mutableListOf<Set<Attribute>>()
        val firstSet = lists.first()
        val remainingSets = lists.drop(1)

        for (element in firstSet) {
            for (combination in findCombinationsRecursively(remainingSets)) {
                result.add(setOf(element) + combination)
            }
        }
        return result
    }

    private fun filterCombinations(
        constraints: Set<Constraint>, combinations: List<Set<Attribute>>
    ): List<Set<Attribute>> {
        return combinations.filter { combination ->
            constraints.filter { it.operator == Operator.EQUALITY }.all {
                applyEqualityConstraint(it, combination)
            }
        }
    }

    private fun applyEqualityConstraint(constraint: Constraint, combination: Set<Attribute>): Boolean =
        !combination.contains(constraint.attributes.first()) && !combination.contains(constraint.attributes.last()) || combination.contains(
            constraint.attributes.first()
        ) && combination.contains(constraint.attributes.last())
}