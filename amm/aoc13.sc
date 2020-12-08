// Advent of code Day 7 Puzzle 13
//

import $file.GetFileInput
import scala.util.matching.Regex
import scala.annotation.tailrec

val bagPattern: Regex = raw"(\d+)\s(\S+\s\S+)\sbags?[,.]?".r

class Bag(val name: String){

  override def toString(): String = s"Bag(${name})"

}

class BagTree(val parent: Bag, val children: Map[Bag, Int]){

  def hasChild(bag: Bag): Boolean =
    children.keys.exists(_.name == bag.name)

  override def toString(): String = s"((${parent} with ${children}))"

}

def parseRowIntoBagTree(row: String): BagTree  =
  new BagTree(getParentFromRow(row), getChildrenFromRow(row))

def getParentFromRow(row: String): Bag = 
  new Bag(row.take(row.indexOf("bags contain")).trim())

def getChildrenFromRow(row: String): Map[Bag, Int] = 
  parseChildren(row.drop(row.indexOf("bags contain") + 13))

def parseChildren(children: String): Map[Bag, Int] =
  children match {
    case "no other bags." => Map()
    case _ => parseChildList(Map(), children)
  }

@tailrec
def parseChildList(branches: Map[Bag, Int], children: String): Map[Bag, Int] =
  children.indexOf(",") match {
    case -1 => branches ++ matchBagPattern(children)
    case index: Int => 
      parseChildList(branches ++ matchBagPattern(children.take(index)), children.drop(index+1).trim())
  }

def matchBagPattern(bagDescription: String): Map[Bag, Int] =
  bagDescription match {
    case bagPattern(num, name) => Map(new Bag(name) -> num.toInt)
    case _ => throw new MatchError("\""+bagDescription+"\"")
  }

def findTreesWithChild(trees: List[BagTree], bag: Bag): List[BagTree] =
  trees.filter(_.hasChild(bag))

@tailrec
def findAllEventualParents(parentsLeft: List[Bag], parentsRight: List[Bag], bagTrees: List[BagTree]): List[Bag] =
  if (parentsRight.isEmpty) parentsLeft
  else {
    val treesWithChild = findTreesWithChild(bagTrees, parentsRight.head)
    val parentsWithChild = treesWithChild.map(_.parent)
    val newParents = onlyNewParents(parentsLeft ++ parentsRight, parentsWithChild)
    findAllEventualParents(parentsLeft :+ parentsRight.head, parentsRight.tail ++ newParents, bagTrees)
  }

def onlyNewParents(parents: List[Bag], newParents: List[Bag]): List[Bag] =
  newParents.filter(x => !parents.exists(y => y.name == x.name))

val input: List[String] = GetFileInput.readFile("../data/day07.input")

val bagTrees: List[BagTree] = for (row <- input) yield parseRowIntoBagTree(row)

val initialParents = findTreesWithChild(bagTrees, new Bag("shiny gold")).map(_.parent)
val eventualParents = findAllEventualParents(List(), initialParents, bagTrees)

println(s"Number of eventual parents ${eventualParents.length}")

