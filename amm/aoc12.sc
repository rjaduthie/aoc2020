// AoC day 6 puzzle 11

import $file.GetFileInput

val groupedInput: List[String] = GetFileInput.readBlankLineGroupedFile("../data/day06.input")
val groupSizes: List[Int] = for (group <- groupedInput) yield
  group.filter(_ == ' ').length + 1

println(groupedInput.take(10))
println(groupSizes.take(10))

val allYesesPerGroup: List[Int] = for (group <- groupedInput.zip(groupSizes)) yield
  group._1
    .filter(_ != ' ').groupBy(x => x)
    .filter(x => x._2.length == group._2)
    .keys.toList.length

val sumOfYeses = allYesesPerGroup.foldLeft(0)(_ + _)

println(s"Sum of the yeses for all groups is ${sumOfYeses}")

