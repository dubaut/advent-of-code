package dev.buildingdragons.aoc2020

import dev.buildingdragons.aoc2020.day01.Day01
import dev.buildingdragons.aoc2020.day02.Day02

object AdventOfCode extends App {
  println("*** Advent of Code 2020, done in Scala ***")

  println("Day 01: ")
  Day01.answer match {
    case Right(result) => println(result)
    case Left(error) => println(s"ERROR: $error")
  }

  println

  println("Day 02: ")
  val day02 = Day02.answer
  println(s"  Number of valid passwords: ${day02.numValidPasswords}")

  println
}