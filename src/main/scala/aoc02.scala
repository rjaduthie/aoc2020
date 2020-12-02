// Advent of code Day 1 Puzzle 2

package AdventOfCode

object ElfChecker {

  val TARGET_SUM = 2020

  def checkThreeWaySum(numbers: List[Int]): Option[Int] = {
    @scala.annotation.tailrec
    def checkHeadAgainstTails(number: Int, numbersLeft: List[Int], numbersRight: List[Int]): Option[Int] =
      numbersRight.find(_ + number + numbersLeft.last == TARGET_SUM) match {
        case Some(value) => return Some(number * numbersLeft.last * value)
        case None =>
          if (numbersLeft.length < 2)
            return None
          else if (numbersRight.isEmpty)
            checkHeadAgainstTails(numbersLeft.head, List(numbersLeft.tail.head), numbersLeft.tail.tail)
          else
            checkHeadAgainstTails(number, numbersLeft :+ numbersRight.head, numbersRight.tail)
      }
    checkHeadAgainstTails(numbers.head, List(numbers.tail.head), numbers.tail.tail)
  }

}