package dev.buildingdragons.aoc2020.day04

import dev.buildingdragons.aoc2020.day04.Passport.Record.{BirthYear, CountryId, ExpirationYear, EyeColor, HairColor, Height, IssueYear, PassportId}
import org.apache.commons.lang3.StringUtils

final case class Passport(birthYear: String,
                          issueYear: String,
                          expirationYear: String,
                          height: String,
                          hairColor: String,
                          eyeColor: String,
                          passportId: String,
                          countryId: Option[String])

object Passport {

  def isValid(passport: Passport): Boolean = {
    BirthYear.isValid(passport.birthYear) &&
      IssueYear.isValid(passport.issueYear) &&
      ExpirationYear.isValid(passport.expirationYear) &&
      Height.isValid(passport.height) &&
      HairColor.isValid(passport.hairColor) &&
      EyeColor.isValid(passport.eyeColor) &&
      PassportId.isValid(passport.passportId)
  }

  private val requiredRecords = Set(BirthYear.key, IssueYear.key, ExpirationYear.key, Height.key, HairColor.key, EyeColor.key, PassportId.key)

  def apply(input: String): Option[Passport] = {
    val entries = StringUtils.split(input, " ").map { entry =>
      val keyValue = StringUtils.split(entry, ":")
      keyValue(0) -> keyValue(1)
    }.toMap

    if (requiredRecords.subsetOf(entries.keySet)) {
      Some(Passport(
        entries(BirthYear.key),
        entries(IssueYear.key),
        entries(ExpirationYear.key),
        entries(Height.key),
        entries(HairColor.key),
        entries(EyeColor.key),
        entries(PassportId.key),
        entries.get(CountryId.key)
      ))
    } else {
      None
    }
  }

  sealed trait Record {
    val key: String

    def isValid(value: String): Boolean
  }

  object Record {

    case object BirthYear extends Record {
      private val regex = "^(\\d{4})$".r

      override val key: String = "byr"

      override def isValid(value: String): Boolean = value match {
        case regex(value) =>
          val valueAsInt = value.toInt
          valueAsInt >= 1920 && valueAsInt <= 2002
        case _ => false
      }

    }

    case object IssueYear extends Record {
      private val regex = "^(\\d{4})$".r

      override val key: String = "iyr"

      override def isValid(value: String): Boolean = value match {
        case regex(value) =>
          val valueAsInt = value.toInt
          valueAsInt >= 2010 && valueAsInt <= 2020
        case _ => false
      }
    }

    case object ExpirationYear extends Record {
      private val regex = "^(\\d{4})$".r

      override val key: String = "eyr"

      override def isValid(value: String): Boolean = value match {
        case regex(value) =>
          val valueAsInt = value.toInt
          valueAsInt >= 2020 && valueAsInt <= 2030
        case _ => false
      }
    }

    case object Height extends Record {
      private val regex = "^(\\d+)(in|cm)$".r

      override val key: String = "hgt"

      override def isValid(value: String): Boolean = value match {
        case regex(value, unit) =>
          val valueAsInt = value.toInt

          unit match {
            case "cm" => valueAsInt >= 150 && valueAsInt <= 193
            case "in" => valueAsInt >= 59 && valueAsInt <= 76
            case _ => false
          }
        case _ => false
      }
    }

    case object HairColor extends Record {
      private val regex = "^(#[0-9a-f]{6})$".r

      override val key: String = "hcl"

      override def isValid(value: String): Boolean = value match {
        case regex(_) => true
        case _ => false
      }
    }

    case object EyeColor extends Record {
      private val validEyeColors = Set("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

      override val key: String = "ecl"

      override def isValid(value: String): Boolean = validEyeColors.contains(value)
    }

    case object PassportId extends Record {
      private val regex = "^(\\d{9})$".r

      override val key: String = "pid"

      override def isValid(value: String): Boolean = value match {
        case regex(_) => true
        case _ => false
      }
    }

    case object CountryId extends Record {
      override val key: String = "cid"

      override def isValid(value: String): Boolean = ???
    }

  }

}
