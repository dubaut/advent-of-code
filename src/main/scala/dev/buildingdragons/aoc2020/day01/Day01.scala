package dev.buildingdragons.aoc2020.day01

import dev.buildingdragons.aoc2020.Util

import scala.annotation.tailrec

object Day01 {
  def summands(expanses: List[Int], n: Int): List[Int] = group(expanses)(n).filter(_.sum == 2020).head

  @tailrec
  def group(input: List[Int], groups: List[List[Int]] = Nil)(n: Int): List[List[Int]] = {
    if (groups.isEmpty) {
      group(input, input.map(_ :: Nil))(n)
    } else {
      val result = groups.flatMap { element =>
        (input diff element).map { x =>
          (element ++ List(x)).sorted
        }
      }.distinct

      if (result.head.length == n) {
        result
      } else {
        group(input, result)(n)
      }
    }
  }

  def solvePart1(expanses: List[Int]): Int = summands(expanses, 2).product

  def solvePart2(expanses: List[Int]): Int = summands(expanses, 3).product

  def answer: Either[Any, Result] = {
    Util.loadTextFileResource("day01/input.txt").toEither match {
      case Left(throwable) => Left(throwable)
      case Right(lines) =>
        val expanses = lines.map(_.toInt)

        val part1 = solvePart1(expanses)
        val part2 = solvePart2(expanses)

        Right(Result(part1, part2))
    }
  }

  final case class Result(part1: Int, part2: Int)

}