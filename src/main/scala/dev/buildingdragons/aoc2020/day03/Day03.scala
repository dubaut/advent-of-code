package dev.buildingdragons.aoc2020.day03

import dev.buildingdragons.aoc2020.Util

import scala.util.{Failure, Success}

object Day03 {
  def createMap(lines: List[String], lateralFactor: Int): List[String] = {
    val verticalDistance = lines.size
    val inputWidth = lines.head.length
    val numMapCopies = math.ceil((verticalDistance * lateralFactor) / inputWidth).toInt

    lines.map(line => line * numMapCopies)
  }

  def countTrees(map: List[String]): Int = {
    val lateral = 3

    map.zipWithIndex.map { item =>
      val (row, index) = item
      val pos = if (index == 0) 0 else lateral * index

      val c = row.charAt(pos)
      c match {
        case '#' => 1
        case _ => 0
      }
    }.sum
  }

  def answer: Result = Util.loadTextFileResource("day03/input.txt") match {
    case Failure(exception) => throw exception
    case Success(input) =>
      val map = createMap(input, 4)
      val numberOfTrees = countTrees(map)

      Result(numberOfTrees)
  }

  final case class Result(numTrees: Int)

  sealed trait MapEntry

  object MapEntry {

    case object OpenSpace extends MapEntry

    case object Tree extends MapEntry

  }

}