#!/usr/bin/env amm
// requires Ammonite to run as script: https://ammonite.io

import $file.aoc09

val minCode = aoc09.decodedPasses.min
val maxCode = aoc09.decodedPasses.max

println(s"Min ${minCode} max ${maxCode}")

val untakenSeat = (minCode to maxCode).filter(x => !aoc09.decodedPasses.exists(y => y == x))

println(s"Seat ${untakenSeat(0)} not taken")
// if (untakenSeat.length == 1)
//   println(s"Seat ${untakenSeat(0)} not taken")
// else
//   println(s"ERROR: Number of untaken seats is: ${untakenSeat.length}")

