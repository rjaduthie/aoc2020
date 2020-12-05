#!/usr/bin/env amm
// requires Ammonite to run as script: https://ammonite.io

import scala.util.matching.Regex

import $file.GetFileInput

val separator: String = ":"
val hgtRegex: Regex = raw"(\d+)(in|cm)".r
val hclRegex: Regex = raw"#[a-f0-9]{6}".r
val eclRegex: Regex = raw"(amb|blu|brn|gry|grn|hzl|oth)".r
val pidRegex: Regex = raw"\d{9}".r

val passportInput: List[String] = GetFileInput.readFile("../data/day04.input")

val passportDetails: List[String] = passportInput.foldLeft(List(""))(collateDetails)

def collateDetails(collatedDetails: List[String], currentDetails: String): List[String] = 
  if (currentDetails.trim.isEmpty) collatedDetails :+ currentDetails
  else collatedDetails.init :+ (collatedDetails.last + " " + currentDetails).trim

def validatePassport(passportInfo: String): Boolean =
  requiredFieldsValidation.forall({case ((x,y)) => applyValidation(passportInfo)(x,y)})

val requiredFieldsValidation: Map[String,String=>Boolean] = Map(
  "ecl"->eclRegex.matches,
  "pid"->pidRegex.matches, 
  "eyr"->validateYear(2020,2030), 
  "hcl"->hclRegex.matches,
  "byr"->validateYear(1920,2002), 
  "iyr"->validateYear(2010,2020), 
  "hgt"->validateHeight
)

def validateYear(minYear: Int, maxYear: Int)(year: String): Boolean = 
  minYear <= year.toInt && year.toInt <= maxYear

def validateHeight(hgt: String): Boolean = hgt match {
  case hgtRegex(num, unit) => 
    if(unit == "cm") 150 <= num.toInt && num.toInt <= 193 
    else 59 <= num.toInt && num.toInt <= 76
  case _ => false
}

def applyValidation(details: String)(key: String, validator: String=>Boolean): Boolean =
    if (details.indexOf(key) == -1) false
    else validator(extractData(details, key))
    
def extractData(details: String, key: String): String = {
    val keyIndex = details.indexOf(key)
    val valueIndex = keyIndex + key.length + separator.length
    val endIndex = details.indexOf(" ", keyIndex) 
    if (endIndex == -1) details.substring(valueIndex)
    else details.substring(valueIndex, endIndex)
}

val validPassports: List[String] = passportDetails.filter(validatePassport)

println(s"There are ${validPassports.length} valid passports.")
