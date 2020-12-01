package dev.buildingdragons.aoc2020.day01

import dev.buildingdragons.aoc2020.Util

object Day01 {
  def findSummands(expanseReport: List[Int]): (Int, Int) = {
    (1 until expanseReport.length).map { index =>
      val value = expanseReport(index)
      val values = expanseReport.filter(_ != value)
      (value, values)
    }.flatMap { item =>
      item._2.map { x =>
        val list = (item._1 :: x :: Nil).sorted
        (list.head, list(1))
      }
    }.filter(value => value._1 + value._2 == 2020).head
  }

  def solvePart1(expanses: List[Int]): Int = {
    val summands = findSummands(expanses)
    summands._1 * summands._2
  }

  def answer: Either[Any, Result] = {
    val part1 = Util.loadTextFileResource("day01/input.txt").toEither match {
      case Left(throwable) => Left(throwable)
      case Right(lines) =>
        val expanses = lines.map(_.toInt)
        val summands = findSummands(expanses)
        Right(summands._1 * summands._2)
    }

    part1 match {
      case Left(value) => Left(value)
      case Right(part1) =>
        Right(Result(part1))
    }
  }

  final case class Result(part1: Int)

}