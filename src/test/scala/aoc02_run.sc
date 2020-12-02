import AdventOfCode.ElfChecker

import scala.io.{BufferedSource, Source}

val testNumbers = List(10, 20, 30, 40, 50, 60, 70, 80, 90)
val TARGET_SUM = 60
val NUMBER_FILE = "/home/rjaduthie/workspaces/aoc2020/data/aoc01.input"
val NUMBER_SOURCE: BufferedSource = Source.fromFile(NUMBER_FILE)
val NUMBERS: List[Int] = NUMBER_SOURCE.getLines.toList.map(_.toInt)
NUMBER_SOURCE.close

ElfChecker.checkThreeWaySum(NUMBERS) match {
  case Some(value) => println(value)
  case None => println("No candidates found.")
}