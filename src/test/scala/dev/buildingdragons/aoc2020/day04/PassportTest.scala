package dev.buildingdragons.aoc2020.day04

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers

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
}
