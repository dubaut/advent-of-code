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
  }

  object Record {

    case object BirthYear extends Record {
      override val key: String = "byr"
    }

    case object IssueYear extends Record {
      override val key: String = "iyr"
    }

    case object ExpirationYear extends Record {
      override val key: String = "eyr"
    }

    case object Height extends Record {
      override val key: String = "hgt"
    }

    case object HairColor extends Record {
      override val key: String = "hcl"
    }

    case object EyeColor extends Record {
      override val key: String = "ecl"
    }

    case object PassportId extends Record {
      override val key: String = "pid"
    }

    case object CountryId extends Record {
      override val key: String = "cid"
    }

  }

}
