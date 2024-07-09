package com.xdata.zebrapuzzle

import com.xdata.zebrapuzzle.dto.*
import com.xdata.zebrapuzzle.dto.Operator.*
import kotlin.math.abs

class BackTrackingPuzzleSolver : PuzzleSolver {
    override fun solvePuzzle(rules: Rules, possibleCombinations: List<Set<Attribute>>): List<Domain>? {
        return solvePuzzle(rules.constraints, List(rules.domainsNumber) { Domain() }, 0, possibleCombinations)
    }

    private fun solvePuzzle(
        constraints: Set<Constraint>, domains: List<Domain>, index: Int = 0, possibleCombinations: List<Set<Attribute>>
    ): List<Domain>? {
        if (index == domains.size) {
            return if (isValidByConstraints(constraints, domains)) domains else null
        }
        for (combination in possibleCombinations) {
            for (attribute in combination) {
                domains[index].attributesSet.add(attribute)
            }
            val narrowedCombinations = possibleCombinations.filter { it.intersect(combination).isEmpty() }
            if (solvePuzzle(constraints, domains, index + 1, narrowedCombinations) != null) {
                return domains
            } else {
                domains[index].attributesSet.clear()
            }
        }
        return null
    }


    private fun isValidByConstraints(constraints: Set<Constraint>, domains: List<Domain>): Boolean {
        return constraints.all { constraint ->
            applyConstraint(constraint, domains)
        }
    }

    private fun applyConstraint(
        constraint: Constraint,
        domains: List<Domain>
    ): Boolean {
        when (constraint.operator) {
            EQUALITY -> {
                val firstDomain =
                    domains.firstOrNull { domain -> domain.attributesSet.contains(constraint.attributes.first()) }
                val secondDomain =
                    domains.firstOrNull { domain -> domain.attributesSet.contains(constraint.attributes.last()) }
                if (firstDomain == null || secondDomain == null || firstDomain != secondDomain) return false
            }

            POSITION -> {
                val domain = domains.getOrNull(constraint.position!! - 1) ?: return false
                if (!domain.attributesSet.contains(constraint.attributes.first())) return false
            }

            ORDER -> {
                val firstDomainIndex =
                    domains.indexOfFirst { domain -> domain.attributesSet.contains(constraint.attributes.first()) }
                val secondDomainIndex =
                    domains.indexOfFirst { domain -> domain.attributesSet.contains(constraint.attributes.last()) }
                if (firstDomainIndex == -1 || secondDomainIndex == -1 || firstDomainIndex != secondDomainIndex - 1) return false
            }

            NEIGHBOUR -> {
                val firstDomainIndex =
                    domains.indexOfFirst { domain -> domain.attributesSet.contains(constraint.attributes.first()) }
                val secondDomainIndex =
                    domains.indexOfFirst { domain -> domain.attributesSet.contains(constraint.attributes.last()) }
                if (firstDomainIndex == -1 || secondDomainIndex == -1 || abs(firstDomainIndex - secondDomainIndex) != 1) return false
            }
        }
        return true
    }

}