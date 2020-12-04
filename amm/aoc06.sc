#!/usr/bin/env amm
// requires Ammonite to run as script: https://ammonite.io

import $file.GetFileInput

val input: List[String] = GetFileInput.readFile("../data/day03.input")
val tree: Char = '#'
val slopes = List((1, 1),(3, 1),(5, 1),(7, 1),(1, 2))

val courses = 
  for ((rightStep, downStep) <- slopes) yield {
    for ((row, index) <- input.zipWithIndex) yield {
      if(index % downStep == 0) {
        val rightCoord: Int = (index*(rightStep.toFloat/downStep)).toInt
        row(rightCoord % row.length)
      } else ' '
    }
  }
val treeCounts: List[Int] = for(course <- courses) yield course.count(_ == tree)
val product: Int = treeCounts.foldLeft(1)((x,y) => x*y)
println(s"The product of the tree counts of each course is ${product}")
