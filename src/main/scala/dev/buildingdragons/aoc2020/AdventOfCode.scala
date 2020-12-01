package dev.buildingdragons.aoc2020

import dev.buildingdragons.aoc2020.day01.Day01

object AdventOfCode extends App {
  println("*** Advent of Code 2020, done in Scala ***")

  print("Day 01: ")
  Day01.answer match {
    case Right(result) => println(result)
    case Left(error) => println(s"ERROR: $error")
  }
}