package dev.buildingdragons.aoc2020.day01

import dev.buildingdragons.aoc2020.day01.Day01.{summands, group, solvePart1, solvePart2}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers

class Day01Test extends AnyFlatSpec with Matchers {
  "summands" must "must return those two elements that add up to 2020." in {
    val expenses = List(366, 979, 1721, 299, 675, 1456)

    val expected = List(1721, 299)
    val actual = summands(expenses, 2)

    actual must contain only (expected: _*)
  }

  "group" must "return number groups." in {
    val input = List(1, 2, 3)
    val n = 2

    val expected = List(List(1, 2), List(1, 3), List(2, 3))
    assertResult(expected) {
      group(input)(n)
    }
  }

  "solvePart1" must "return 514579 for 366, 979, 1721, 299, 675, and 1456." in {
    assertResult(514579) {
      solvePart1(List(366, 979, 1721, 299, 675, 1456))
    }
  }

  "solvePart2" must "return 241861950 for 366, 979, 1721, 299, 675, and 1456." in {
    assertResult(241861950) {
      solvePart2(List(366, 979, 1721, 299, 675, 1456))
    }
  }
}