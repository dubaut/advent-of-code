package dev.buildingdragons.aoc2020.day01

import dev.buildingdragons.aoc2020.day01.Day01.{answer, findSummands, solvePart1}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers

class Day01Test extends AnyFlatSpec with Matchers {
  "findSummands" must "must return those two elements that add up to 2020." in {
    val expanseReport = List(366, 979, 1721, 299, 675, 1456)

    assertResult((299, 1721)) {
      findSummands(expanseReport)
    }
  }

  "solvePart1" must "return 514579 for 366, 979, 1721, 299, 675, and 1456." in {
    assertResult(514579) {
      solvePart1(List(366, 979, 1721, 299, 675, 1456))
    }
  }
}