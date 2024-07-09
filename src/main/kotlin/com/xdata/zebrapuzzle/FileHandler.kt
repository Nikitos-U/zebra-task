package com.xdata.zebrapuzzle

import com.xdata.zebrapuzzle.dto.Domain
import com.xdata.zebrapuzzle.dto.Rules

interface FileHandler {
    fun readRulesFromJson(filePath: String): Rules

    fun writeSolutionToJson(filePath: String, solution: List<Domain>?)
}