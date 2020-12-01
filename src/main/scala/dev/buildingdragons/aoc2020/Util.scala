package dev.buildingdragons.aoc2020

import scala.io.Source
import scala.util.{Try, Using}

object Util {
  def loadTextFileResource(resource: String): Try[List[String]] = Using(Source.fromResource(resource))(_.getLines().toList)
}