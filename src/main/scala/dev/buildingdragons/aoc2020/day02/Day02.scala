package dev.buildingdragons.aoc2020.day02

import dev.buildingdragons.aoc2020.Util

import scala.util.{Failure, Success}

object Day02 {
  def answer: Result = Util.loadTextFileResource("day02/input.txt") match {
    case Failure(exception) => throw exception
    case Success(lines) =>
      val validSledgeRental = validateSledgeRental(lines)
      val validToggobanCorporate = validateToggobanCorporate(lines)
      Result(validSledgeRental.length, validToggobanCorporate.length)
  }

  def countLetters(password: String): Map[Char, Int] = {
    val passwordAsList = password.toList
    val distinctLetters = passwordAsList.distinct
    distinctLetters.map(letter => (letter, passwordAsList.count(_ == letter))).toMap
  }

  def validateSledgeRental(lines: List[String]): List[PasswordLine] =
    lines.map(PasswordLine(_)).filter { passwordLine =>
      val lettersMap = countLetters(passwordLine.password)

      lettersMap.get(passwordLine.char) match {
        case None => false
        case Some(n) => n >= passwordLine.val1 && n <= passwordLine.val2
      }
    }

  def validateToggobanCorporate(lines: List[String]): List[PasswordLine] =
    lines.map(PasswordLine(_)).filter { passwordLine =>
      val passwordAsList = passwordLine.password.toList

      val char1 = if (passwordAsList.size >= passwordLine.val1) passwordAsList(passwordLine.val1 - 1) else Char.MaxValue
      val char2 = if (passwordAsList.size >= passwordLine.val2) passwordAsList(passwordLine.val2 - 1) else Char.MaxValue

      (char1 != char2) && (char1 == passwordLine.char || char2 == passwordLine.char)
    }

  final case class Result(numSledgeRental: Int, numToggobanCorporate: Int)

  final case class PasswordLine(char: Char, val1: Int, val2: Int, password: String)

  object PasswordLine {
    private val regexPasswordLine = "^(\\d+)-(\\d+) ([a-z]): ([a-z]+)$".r

    def apply(line: String): PasswordLine = line match {
      case regexPasswordLine(val1, val2, letter, password) =>
        PasswordLine(letter.charAt(0), val1.toInt, val2.toInt, password)
      case _ => throw new IllegalArgumentException(s"Illegal password line format: $line")
    }
  }

}