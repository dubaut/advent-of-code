package dev.buildingdragons.aoc2020

import dev.buildingdragons.aoc2020.day01.Day01
import dev.buildingdragons.aoc2020.day02.Day02
import dev.buildingdragons.aoc2020.day03.Day03
import dev.buildingdragons.aoc2020.day04.Day04

object AdventOfCode extends App {
  println("*** Advent of Code 2020, done in Scala ***")

  println("Day 01: ")
  val day01 = Day01.answer
  println(s" Result:  $day01")

  println

  println("Day 02: ")
  val day02 = Day02.answer
  println(s"  Number of valid passwords (Sledge Rental): ${day02.numSledgeRental}")
  println(s"  Number of valid passwords (Toggoban Corp): ${day02.numToggobanCorporate}")

  println

  println("Day 03: ")
  val day03 = Day03.answer
  println(s"  Number of trees encountered:                              ${day03.part1}")
  println(s"  Product of the number of trees encountered on all slopes: ${day03.part2}")

  println("Day 04: ")
  val day04 = Day04.answer
  println(s"  Number of valid passports:     ${day04.part1}")
  println(s"  Number of validated passports: ${day04.part2}")
}