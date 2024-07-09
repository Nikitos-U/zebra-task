package com.xdata.zebrapuzzle.dto

data class Constraint(
    val attributes: Set<Attribute>,
    val position: Int? = null,
    val operator: Operator
)
