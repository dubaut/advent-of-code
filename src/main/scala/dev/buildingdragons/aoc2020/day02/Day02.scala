package dev.buildingdragons.aoc2020.day02

import dev.buildingdragons.aoc2020.Util

import scala.util.{Failure, Success}

object Day02 {
  def answer: Result = Util.loadTextFileResource("day02/input.txt") match {
    case Failure(exception) => throw exception
    case Success(lines) =>
      val validPasswordLines = validatePasswordLines(lines)
      Result(validPasswordLines.length)
  }

  def countLetters(password: String): Map[Char, Int] = {
    val passwordAsList = password.toList
    val distinctLetters = passwordAsList.distinct
    distinctLetters.map(letter => (letter, passwordAsList.count(_ == letter))).toMap
  }

  def validatePasswordLines(lines: List[String]): List[PasswordLine] =
    lines.map(PasswordLine(_)).filter { passwordLine =>
      val lettersMap = countLetters(passwordLine.password)

      lettersMap.get(passwordLine.char) match {
        case None => false
        case Some(n) => n >= passwordLine.min && n <= passwordLine.max
      }
    }

  final case class Result(numValidPasswords: Int)

  final case class PasswordLine(char: Char, min: Int, max: Int, password: String)

  object PasswordLine {
    private val regexPasswordLine = "^(\\d+)-(\\d+) ([a-z]): ([a-z]+)$".r

    def apply(line: String): PasswordLine = line match {
      case regexPasswordLine(min, max, letter, password) =>
        PasswordLine(letter.charAt(0), min.toInt, max.toInt, password)
      case _ => throw new IllegalArgumentException(s"Illegal password line format: $line")
    }
  }

}