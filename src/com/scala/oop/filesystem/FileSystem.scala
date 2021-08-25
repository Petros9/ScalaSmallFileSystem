package com.scala.oop.filesystem

import com.scala.oop.commands.Command
import com.scala.oop.files.Directory

import java.util.Scanner

object FileSystem extends App {

  val root = Directory.ROOT

  io.Source.stdin.getLines().foldLeft(State(root, root)) ((currentState, newLine) => {
    currentState.show()
    Command.from(newLine).apply(currentState)
  })

  /*
  val scanner = new Scanner(System.in)
  var state = State(root, root)

  while(true){
    state.show()
    val input = scanner.nextLine()
    state = Command.from(input).apply(state)
  }
  */
}
