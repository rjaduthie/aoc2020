#!/usr/bin/env scalas
// Advent of code Day 2 Puzzle 4

import scala.io.Source
import scala.util.matching.Regex

val PASSWORD_FILE = "../../../data/aoc03.input"
val TARGET_SUM = 2020

val PASSWORD_SOURCE = Source.fromFile(PASSWORD_FILE)
val PASSWORDS_WITH_RULES: List[String] = PASSWORD_SOURCE.getLines.toList
PASSWORD_SOURCE.close

val PASSWORD_RULES_REGEX: Regex = raw"(\d+)-(\d+)\s([a-z]):\s([a-z]+)".r
println(s"The number of valid passwords in the file is ${
  (
    for (password_with_rule <- PASSWORDS_WITH_RULES)
      yield password_with_rule match {
        case PASSWORD_RULES_REGEX(index_lo, index_hi, rule_char, password) =>
          val char_ = rule_char.toList.head
          val password_ = password.toList
          (password_(index_lo.toInt - 1) == char_) ^ (password_(index_hi.toInt - 1) == char_)
      }
  ).count(_ == true)
}")
