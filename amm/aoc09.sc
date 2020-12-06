#!/usr/bin/env amm
// requires Ammonite to run as script: https://ammonite.io

import $file.GetFileInput
import scala.math.pow

val boardingPassInput: List[String] = 
  GetFileInput.readFile("../data/day05.input")

def decodeBoardingPass(code: String): Int =
  code.zipWithIndex.foldRight(0)((x, acc) => constructDecimal(code.length)(acc, x))
  
def constructDecimal(bitLength: Int)(acc: Int, args: Tuple2[Char, Int]): Int =  
  args match {
    case (unit, index) =>
      if (unit == 'B' || unit == 'R') 
        acc + pow(2, bitLength-index-1).toInt
      else
        acc 
  }

val decodedPasses = for (code <- boardingPassInput) 
  yield decodeBoardingPass(code)


println(decodeBoardingPass("BBBBBBBRRR"))
println(decodeBoardingPass("FFFFFFFLLL"))
println(decodeBoardingPass("FFFFFFFLLR"))
println(s"The maximum boarding pass number is ${decodedPasses.max}")
