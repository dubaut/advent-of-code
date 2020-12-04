package dev.buildingdragons.aoc2020.day03

import dev.buildingdragons.aoc2020.Util

import scala.util.{Failure, Success}

object Day03 {
  def createMap(lines: List[String], slope: Slope): List[String] = {
    val verticalDistance = lines.size
    val inputWidth = lines.head.length
    val numMapCopies = math.ceil((verticalDistance * (slope.right + 1)) / inputWidth).toInt

    lines.map(line => line * numMapCopies)
  }

  def countTrees(input: List[String], slope: Slope): Int = {
    val map = createMap(input, slope)

    map.zipWithIndex.map { item =>
      val (row, index) = item

      if (index % slope.down == 0) {
        val step = index / slope.down
        val pos = if (index == 0) 0 else slope.right * step

        val c = row.charAt(pos)
        c match {
          case '#' => 1
          case _ => 0
        }
      } else {
        0
      }
    }.sum
  }

  def answer: Result = Util.loadTextFileResource("day03/input.txt") match {
    case Failure(exception) => throw exception
    case Success(input) =>
      val slopes = Slope(1, 1) :: Slope(3, 1) :: Slope(5, 1) :: Slope(7, 1) :: Slope(1, 2) :: Nil

      val numberOfTrees = countTrees(input, Slope(3, 1))
      val product = slopes.map(slope => countTrees(input, slope)).product

      Result(numberOfTrees, product)
  }

  final case class Result(part1: Int, part2: Int)

  final case class Slope(right: Int, down: Int)

}