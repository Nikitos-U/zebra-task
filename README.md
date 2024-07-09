**ZEBRA PUZZLE SOLVER**

Kotlin implementation of [zebra puzzle](https://en.wikipedia.org/wiki/Zebra_Puzzle) solver with the use of a backtracking algorithm.

Puzzle rules are read from json file [zebra-puzzle-rules.json](src%2Fmain%2Fresources%2Fzebra-puzzle-rules.json)
Rules should be in json with the structure that complies to Rules.kt dto object

```json
{
  "domainsNumber": 5,
  "attributes": [
    {
      "type": "drinks",
      "name": "water"
    },
    {
      "type": "pets",
      "name": "zebra"
    }
  ],
  "constraints": [
    {
      "attributes": [
        {
          "type": "nationalities",
          "name": "Englishman"
        },
        {
          "type": "colors",
          "name": "red"
        }
      ],
      "operator": "EQUALITY"
    },
...
```
Here `domainsNumber` stays for the number of houses in original puzzle, but could be adjusted to solve puzzles of other dimensions

`attributes` is a set of puzzle attributes that not represented in puzzle constrains. Each attribute holds it's type ("cigarettes", "drinks", "pets"...) and name ("Old Gold", "water", "dog" ...) correspondingly

`constrains` represents set of all puzzle constraints, in a form of attributes set, together with operator applied to them and optional position. Such structure allows user to write any number of constraints that is necessary for solution of puzzle of desired size.

Possible operators could be seen in [Operator.kt](src%2Fmain%2Fkotlin%2Fcom%2Fxdata%2Fzebrapuzzle%2Fdto%2FOperator.kt) which is enum:
```kotlin
enum class Operator {
    NEIGHBOUR,
    ORDER,
    EQUALITY,
    POSITION,
}
```
operator `EQUALITY` means that all attributes of a constraint should be in a same domain (House for original puzzle) 

operator `ORDER` means that domains that contains attributes listed in a constraint should be in exact same order as in a constraint 

operator `NEIGBOUR` means that domains that contains attributes listed in a constraint should be next to each other

operator `POSITION` means that domain that contains attribute listed in a constraint should be on the exact position in solution

**Algorithm**

The solution is found with the use of backtracking algorithm, which is recursive. Nevertheless, in order to speed up the process the number of possible solutions is bounded before the start of recursive part. This is done by first creating all possible combinations of attributes (which is a power of domains number) and then applying all constrains with `EQUALITY` operator to each of these combinations to eliminate those not compliant (only these constraints could be applied to single domain/house).
Thus, for the original puzzle only 134 out of 3125 possible attribute combinations gets to the recursive search, which makes the solution finding process fast.

In order to make the solution extensible - interfaces are provided. Thus, in order to implement other algorithm for the solutions developer would need to implement PuzzleSolver[PuzzleSolver.kt](src%2Fmain%2Fkotlin%2Fcom%2Fxdata%2Fzebrapuzzle%2FPuzzleSolver.kt) interface, without need of any preparations.
