import scala.io.Source

def readFile(filename: String): List[String] = {
  val source = Source.fromFile(filename)
  val rows: List[String] = source.getLines.toList
  source.close
  rows
}
