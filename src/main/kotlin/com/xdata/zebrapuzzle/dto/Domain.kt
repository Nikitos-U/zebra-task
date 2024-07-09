package com.xdata.zebrapuzzle.dto

data class Domain(
    val attributesSet: MutableSet<Attribute> = LinkedHashSet(),
)