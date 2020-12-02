package dev.buildingdragons.aoc2020.day02

import dev.buildingdragons.aoc2020.day02.Day02.{PasswordLine, countLetters, validatePasswordLines}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers

class Day02Test extends AnyFlatSpec with Matchers {
  "countLetters" must "return the number of letters in a password." in {
    assertResult(Map('a' -> 1, 'b' -> 1, 'c' -> 1, 'd' -> 1, 'e' -> 1)) {
      countLetters("abcde")
    }

    assertResult(Map('c' -> 1, 'd' -> 1, 'e' -> 1, 'f' -> 1, 'g' -> 1)) {
      countLetters("cdefg")
    }

    assertResult(Map('c' -> 9, 'x' -> 2)) {
      countLetters("xccccccxccc")
    }
  }

  "validatePasswordLines" must "return a list of all valid password lines." in {
    val lines = "1-3 a: abcde" :: "1-3 b: cdefg" :: "2-9 c: cccccccc" :: Nil

    val expected = PasswordLine('a', 1, 3, "abcde") :: PasswordLine('c', 2, 9, "cccccccc") :: Nil
    val actual = validatePasswordLines(lines)

    actual must equal(expected)
  }
}
