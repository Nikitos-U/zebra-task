package com.xdata.zebrapuzzle

fun main(args: Array<String>) {
    val fileHandler = FileHandlerImpl()
    val rulesHandler = RulesHandlerImpl()

    val rules = fileHandler.readRulesFromJson("src/main/resources/zebra-puzzle-rules.json")

    if (rulesHandler.isValidRules(rules)) {
        val puzzleSolver = BackTrackingPuzzleSolver()
        val solution = puzzleSolver.solvePuzzle(rules, rulesHandler.getPossibleCombinations(rules))
        fileHandler.writeSolutionToJson("src/main/resources/zebra-puzzle-solution.json", solution)
    }
}
