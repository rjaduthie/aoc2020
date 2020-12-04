#!/usr/bin/env amm
// requires Ammonite to run as script: https://ammonite.io

import $file.GetFileInput

val input: List[String] = GetFileInput.readFile("../data/day03.input")
val tree: Char = '#'
val step: Int = 3

val treesOnCourse: List[Boolean] =
	for ((row, index) <- input.zipWithIndex)
		yield row(index*step % row.length) == tree

println(s"The number of trees on the course is ${treesOnCourse.count(_ == true)}")
