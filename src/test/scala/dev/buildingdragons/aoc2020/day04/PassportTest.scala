package dev.buildingdragons.aoc2020.day04

import dev.buildingdragons.aoc2020.day04.Passport.Record.{BirthYear, ExpirationYear, EyeColor, HairColor, Height, IssueYear, PassportId}
import dev.buildingdragons.aoc2020.day04.Passport.isValid
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks._

class PassportTest extends AnyFlatSpec with Matchers {
  "apply" must "return Some(Passport) for a valid passport record." in {
    val input = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"

    val expected = Passport("1937", "2017", "2020", "183cm", "#fffffd", "gry", "860033327", Some("147"))

    val actual = Passport.apply(input)
    actual must contain(expected)
  }

  "apply" must "return Some(Passport) for a passport record without a 'cid' entry." in {
    val input = "hcl:#ae17e1 iyr:2013 eyr:2024 ecl:brn pid:760753108 byr:1931 hgt:179cm"

    val expected = Passport("1931", "2013", "2024", "179cm", "#ae17e1", "brn", "760753108", None)

    val actual = Passport.apply(input)
    actual must contain(expected)
  }

  "apply" must "return None for an invalid passport record." in {
    val input = "hcl:#cfa07d eyr:2025 pid:166559648 iyr:2011 ecl:brn hgt:59in"

    assertResult(None) {
      Passport.apply(input)
    }
  }

  "isValid" must "return true for all passports whose values meet all requirements." in {
    assertResult(true) {
      isValid(Passport("1980", "2012", "2030", "74in", "#623a2f", "grn", "087499704", None))
    }

    assertResult(true) {
      isValid(Passport("1980", "2012", "2030", "74in", "#623a2f", "grn", "087499704", Some("129")))
    }
  }

  "isValid" must "return true for all passports whose values do not meet all requirements." in {
    assertResult(false) {
      isValid(Passport("1926", "2018", "1972", "170", "#18171d", "amb", "186cm", None))
    }
  }

  "BirthYear.isValid" must "return true for a valid and false for an invalid value." in {
    val validValues = Table(
      "valid value",
      "1920",
      "1983",
      "2002"
    )

    val invalidValues = Table(
      "invalid value",
      "abc",
      "12",
      "12345",
      "1919",
      "2003"
    )

    forAll(validValues) { (value: String) =>
      assertResult(true)(BirthYear.isValid(value))
    }

    forAll(invalidValues) { (value: String) =>
      assertResult(false)(BirthYear.isValid(value))
    }
  }

  "IssueYear.isValid" must "return true for a valid and false for an invalid value." in {
    val validValues = Table(
      "valid value",
      "2010",
      "2015",
      "2020"
    )

    val invalidValues = Table(
      "invalid value",
      "abc",
      "12",
      "12345",
      "2009",
      "2021"
    )

    forAll(validValues) { (value: String) =>
      assertResult(true)(IssueYear.isValid(value))
    }

    forAll(invalidValues) { (value: String) =>
      assertResult(false)(IssueYear.isValid(value))
    }
  }

  "ExpirationYear.isValid" must "return true for a valid and false for an invalid value." in {
    val validValues = Table(
      "valid value",
      "2020",
      "2025",
      "2030"
    )

    val invalidValues = Table(
      "invalid value",
      "abc",
      "12",
      "12345",
      "2019",
      "2031"
    )

    forAll(validValues) { (value: String) =>
      assertResult(true)(ExpirationYear.isValid(value))
    }

    forAll(invalidValues) { (value: String) =>
      assertResult(false)(ExpirationYear.isValid(value))
    }
  }

  "Height.isValid" must "return true for a valid and false for an invalid value." in {
    val validValues = Table(
      "valid value",
      "150cm",
      "170cm",
      "193cm",
      "59in",
      "63in",
      "76in"
    )

    val invalidValues = Table(
      "invalid value",
      "abc",
      "123",
      "123xx",
      "149cm",
      "194cm",
      "58in",
      "77in"
    )

    forAll(validValues) { (value: String) =>
      assertResult(true)(Height.isValid(value))
    }

    forAll(invalidValues) { (value: String) =>
      assertResult(false)(Height.isValid(value))
    }
  }

  "HairColor.isValid" must "return true for a valid and false for an invalid value." in {
    val validValues = Table(
      "valid value",
      "#ffffff",
      "#123456",
      "#123fff"
    )

    val invalidValues = Table(
      "invalid value",
      "ffffff",
      "#fffff",
      "#00000",
      "#123ffg"
    )

    forAll(validValues) { (value: String) =>
      assertResult(true)(HairColor.isValid(value))
    }

    forAll(invalidValues) { (value: String) =>
      assertResult(false)(HairColor.isValid(value))
    }
  }

  "EyeColor.isValid" must "return true for a valid and false for an invalid value." in {
    val validValues = Table(
      "valid value",
      "amb",
      "blu",
      "brn",
      "gry",
      "grn",
      "hzl",
      "oth"
    )

    val invalidValues = Table(
      "invalid value",
      "ylw",
      "green",
      ""
    )

    forAll(validValues) { (value: String) =>
      assertResult(true)(EyeColor.isValid(value))
    }

    forAll(invalidValues) { (value: String) =>
      assertResult(false)(EyeColor.isValid(value))
    }
  }

  "PassportId.isValid" must "return true for a valid and false for an invalid value." in {
    val validValues = Table(
      "valid value",
      "000000001",
      "123456789"
    )

    val invalidValues = Table(
      "invalid value",
      "0123456789",
      "12345678",
      "123456abc",
      ""
    )

    forAll(validValues) { (value: String) =>
      assertResult(true)(PassportId.isValid(value))
    }

    forAll(invalidValues) { (value: String) =>
      assertResult(false)(PassportId.isValid(value))
    }
  }
}
