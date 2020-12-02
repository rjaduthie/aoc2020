#!/usr/bin/env scalas
// Advent of code Day 1 Puzzle 2

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
def checkHeadAgainstTails(number: Int, numbersLeft: List[Int], numbersRight: List[Int]): Option[Int] =
  numbersRight.find(_ + number + numbersLeft.last == TARGET_SUM) match {
    case Some(value) => return Some(number * numbersLeft.last * value)
    case None =>
      if (numbersLeft.length + numbersRight.length < 2)
        return None
      else if (numbersRight.isEmpty)
        checkHeadAgainstTails(numbersLeft.head, List(numbersLeft.tail.head), numbersLeft.tail.tail)
      else
        checkHeadAgainstTails(number, numbersLeft :+ numbersRight.head, numbersRight.tail)
  }

checkHeadAgainstTails(NUMBERS.head, List(NUMBERS.tail.head), NUMBERS.tail.tail) match {
  case Some(answer) => println(s"The answer is $answer")
  case None => println("Couldn't find required sum")
}

