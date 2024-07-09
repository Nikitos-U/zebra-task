package com.xdata.zebrapuzzle

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.xdata.zebrapuzzle.dto.Domain
import org.junit.jupiter.api.Assertions.*
import java.io.File
import java.io.FileNotFoundException
import kotlin.test.Test

class FileHandlerImplTest {

    @Test
    fun readFile_rulesParsed() {
        val fileHandler = FileHandlerImpl()
        val filePath = "src/test/resources/good-rules.json"
        val rules = fileHandler.readRulesFromJson(filePath)
        assertEquals(14, rules.constraints.size)
        assertEquals(2, rules.attributes.size)
        assertEquals(5, rules.domainsNumber)
    }

    @Test
    fun readNonExistentFile_exceptionThrown() {
        val fileHandler = FileHandlerImpl()
        val filePath = "src/test/resources/no-such-file.json"
        assertThrows(
            FileNotFoundException::class.java
        ) { fileHandler.readRulesFromJson(filePath) }
    }

    @Test
    fun writeSolutionToFile_fileCreated() {
        val fileHandler = FileHandlerImpl()
        val solution = listOf<Domain>()
        val filePath = "src/test/resources/solution.json"
        fileHandler.writeSolutionToJson(filePath, solution)
        val file = File(filePath)
        assertTrue(file.exists())
        val list = mapper.readValue(file.readText(), List::class.java)
        assertEquals(0, list.size)
        file.delete()
    }

    companion object {
        private val mapper = ObjectMapper().registerKotlinModule()
    }
}