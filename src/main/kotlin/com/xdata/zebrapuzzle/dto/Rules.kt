package com.xdata.zebrapuzzle.dto

data class Rules(
    val domainsNumber: Int,
    val attributes: Set<Attribute>,
    val constraints: Set<Constraint>,
)
