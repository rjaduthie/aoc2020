import scala.io.Source

def readFile(filename: String): List[String] = {
  val source = Source.fromFile(filename)
  val rows: List[String] = source.getLines.toList
  source.close
  rows
}

def readBlankLineGroupedFile(filename: String): List[String] = 
  readFile(filename).foldLeft(List(""))((acc, line) => _collateInput(acc, line))

def _collateInput(collatedInput: List[String], currentLine: String): List[String] = 
  if (currentLine.trim.isEmpty) collatedInput :+ currentLine
  else collatedInput.init :+ (collatedInput.last + " " + currentLine).trim


