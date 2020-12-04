package dev.buildingdragons.aoc2020.day03

import dev.buildingdragons.aoc2020.day03.Day03.{Slope, countTrees, createMap}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers

class Day03Test extends AnyFlatSpec with Matchers {
  "createMap" must "create a full map." in {
    val input = List(
      "..##.......",
      "#...#...#..",
      ".#....#..#.",
      "..#.#...#.#",
      ".#...##..#.",
      "..#.##.....",
      ".#.#.#....#",
      ".#........#",
      "#.##...#...",
      "#...##....#",
      ".#..#...#.#"
    )

    val expected = List(
      "..##.........##.........##.........##.......",
      "#...#...#..#...#...#..#...#...#..#...#...#..",
      ".#....#..#..#....#..#..#....#..#..#....#..#.",
      "..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#",
      ".#...##..#..#...##..#..#...##..#..#...##..#.",
      "..#.##.......#.##.......#.##.......#.##.....",
      ".#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#",
      ".#........#.#........#.#........#.#........#",
      "#.##...#...#.##...#...#.##...#...#.##...#...",
      "#...##....##...##....##...##....##...##....#",
      ".#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#"
    )

    assertResult(expected) {
      val actual = createMap(input, Slope(3, 1))
      actual
    }
  }

  "countTrees" must "return the number of encountered trees." in {
    val input = List(
      "..##.......",
      "#...#...#..",
      ".#....#..#.",
      "..#.#...#.#",
      ".#...##..#.",
      "..#.##.....",
      ".#.#.#....#",
      ".#........#",
      "#.##...#...",
      "#...##....#",
      ".#..#...#.#"
    )

    assertResult(2) {
      countTrees(input, Slope(1, 1))
    }

    assertResult(7) {
      countTrees(input, Slope(3, 1))
    }

    assertResult(3) {
      countTrees(input, Slope(5, 1))
    }

    assertResult(4) {
      countTrees(input, Slope(7, 1))
    }

    assertResult(2) {
      countTrees(input, Slope(1, 2))
    }
  }
}