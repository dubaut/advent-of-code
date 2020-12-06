package dev.buildingdragons.aoc2020.day04

import dev.buildingdragons.aoc2020.Util
import org.apache.commons.lang3.StringUtils

import scala.util.{Failure, Success}

object Day04 {
  def answer: Answer = Util.loadTextFileResource("day04/input.txt") match {
    case Failure(exception) => throw exception
    case Success(input) =>
      val records = getPassportRecords(input)

      val passports = records.flatMap(Passport(_))
      val numValidPassports = passports.count(Passport.isValid)

      Answer(passports.size, numValidPassports)
  }

  def getPassportRecords(input: List[String]): List[String] = {
    val sep1 = Char.MaxValue.toString
    val sep2 = Char.MinValue.toString

    StringUtils.split(
      input.mkString(sep1).replace(sep1 * 2, sep2).replace(sep1, " "),
      sep2
    ).toList
  }

  final case class Answer(part1: Int, part2: Int)

}