#!/usr/bin/env scalas
// Advent of code Day 2 Puzzle 3

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
        case PASSWORD_RULES_REGEX(rule_min, rule_max, rule_char, password) =>
          val char_count: Int = password.count(_ == rule_char.toCharArray.head)
          rule_min.toInt <= char_count && rule_max.toInt >= char_count
      }
  ).count(_ == true)
}")
