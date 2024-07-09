package com.xdata.zebrapuzzle

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.xdata.zebrapuzzle.dto.Domain
import com.xdata.zebrapuzzle.dto.Rules
import java.io.File


class FileHandlerImpl : FileHandler {

    override fun readRulesFromJson(filePath: String): Rules {
        val jsonString = File(filePath).readText()
        return mapper.readValue(jsonString, Rules::class.java)
    }

    override fun writeSolutionToJson(filePath: String, solution: List<Domain>?) {
        val jsonString = mapper.writeValueAsString(solution)
        File(filePath).writeText(jsonString)
    }

    companion object {
        private val mapper = ObjectMapper().registerKotlinModule()
    }
}