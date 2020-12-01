#!/usr/bin/env scalas
// Advent of code Day 1 Puzzle 1

// Requirements (TBC):
// install sbt
// install conscript: http://www.foundweekends.org/conscript/
// run: `cs sbt/sbt`

import scala.io.Source

val NUMBER_FILE = "../../../data/aoc01.input"
val TARGET_SUM = 2020

val NUMBER_SOURCE = Source.fromFile(NUMBER_FILE)
val NUMBERS: List[Int] = NUMBER_SOURCE.getLines.toList.map(_.toInt)
NUMBER_SOURCE.close

@scala.annotation.tailrec
def checkHeadAgainstTail(number: Int, numbers: List[Int]): Int =
    numbers.find(_ + number == TARGET_SUM) match {
        case Some(value) => return number * value
        case None => checkHeadAgainstTail(numbers.head, numbers.tail)
    }

println(checkHeadAgainstTail(NUMBERS.head, NUMBERS.tail))
