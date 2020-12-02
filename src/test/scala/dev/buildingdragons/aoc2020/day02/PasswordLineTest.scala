package dev.buildingdragons.aoc2020.day02

import dev.buildingdragons.aoc2020.day02.Day02.PasswordLine
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers

class PasswordLineTest extends AnyFlatSpec with Matchers{
  "apply" must "parse a password line." in {
    assertResult(PasswordLine('a', 1, 3, "abcde")) {
      PasswordLine("1-3 a: abcde")
    }

    assertResult(PasswordLine('b', 1, 3, "cdefg")) {
      PasswordLine("1-3 b: cdefg")
    }

    assertResult(PasswordLine('c', 2, 9, "ccccccccc")) {
      PasswordLine("2-9 c: ccccccccc")
    }
  }
}
