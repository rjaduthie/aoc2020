#!/usr/bin/env amm
// requires Ammonite to run as script: https://ammonite.io

import $file.GetFileInput

val passportInput: List[String] = GetFileInput.readFile("../data/day04.input")
val requiredFields: List[String] = List("ecl", "pid", "eyr", "hcl", "byr", "iyr", "hgt")
val optionalFields: List[String] = List("cid")

def collateDetails(collatedDetails: List[String], currentDetails: String): List[String] = 
  if (currentDetails.trim.isEmpty) collatedDetails :+ currentDetails
  else collatedDetails.init :+ (collatedDetails.last + " " + currentDetails).trim

def validatePassport(passportInfo: String): Boolean =
  requiredFields.forall(passportInfo.indexOf(_) != -1)

val passportDetails: List[String] = passportInput.foldLeft(List(""))(collateDetails)

val validPassports: List[String] = passportDetails.filter(validatePassport)


println(s"There are ${validPassports.length} valid passports.")
