import $file.GetFileInput
import scala.util.matching.Regex

// declarations

class Instruction(val command: String, val argument: Int){
  override def toString(): String = s"(${command} ${argument})"
}

val instruction: Regex = raw"(\S+)\s([+-]\d+)".r

// statements

val input = GetFileInput.readFile("../data/day08.input")

val inputInstructions: List[Instruction] = (
  for (row <- input) yield
  row match {
    case instruction(command, argument) => new Instruction(command, argument.toInt)
  }
).zipWithIndex 

val programCounter = 0
val accumulator = 0
val commandHistory: List[Int] = List()

def executeNextInstruction(programCounter: Int, accumulator: Int, commandHistory: List[Int]) = {
  // check command history for end condition (duplicated command)
  
  
  inputInstructions(programCounter)._2.command match {
    case "acc" => executeNextInstruction(programCounter + 1, accumulator + inputInstructions(programCounter)._2.argument, commandHistory :+ inputInstructions(programCounter)._1)
    case "nop" => executeNextInstruction(programCounter + 1, accumulator, command :+ inputInstructions(programCounter)._1)
    case "jmp" => executeNextInstruction(programCounter + inputInstructions(programCounter)._2.argument, commandHistory :+ inputInstructions(programCounter)._1)
    case _ => println("Broken!") 
  }
}








  

