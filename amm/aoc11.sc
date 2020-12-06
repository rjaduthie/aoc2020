// AoC day 6 puzzle 11

import $file.GetFileInput

val groupedInput: List[String] = GetFileInput.readBlankLineGroupedFile("../data/day06.input")


val yesesPerGroup: List[Int] = for (group <- groupedInput) yield
  group.filter(_ != ' ').groupBy(x => x).keys.toList.length


val sumOfYeses = yesesPerGroup.foldLeft(0)(_ + _)

println(s"Sum of the yeses for all groups is ${sumOfYeses}")

