
import $file.aoc13


def countAllTheBags(parent: aoc13.Bag, bagTrees: List[aoc13.BagTree]): Int = {
  bagTrees.find(_.parent.name == parent.name) match {
    case Some(bagTree: aoc13.BagTree) => {
        println(bagTree)
        bagTree.children.foldLeft(0)({case (c, (k, v)) => combineChildBagCounts(c, v, k, bagTrees)})
    }
  }
}

def combineChildBagCounts(acc: Int, num: Int, child: aoc13.Bag, bagTrees: List[aoc13.BagTree]): Int = {
  println(s"${acc} + ${num} x ${child.name}")
  acc + num * (1 + countAllTheBags(child, bagTrees))
}


val count = countAllTheBags(new aoc13.Bag("shiny gold"), aoc13.bagTrees)

println(s"Total bag count ${count}")
