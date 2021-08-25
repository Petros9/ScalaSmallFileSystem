package com.scala.oop.filesystem

import com.scala.oop.commands.Command
import com.scala.oop.files.Directory

import java.util.Scanner

object FileSystem extends App {

  val scanner = new Scanner(System.in)

  val root = Directory.ROOT
  var state = State(root, root)

  while(true){
    state.show()
    val input = scanner.nextLine()
    state = Command.from(input).apply(state)
  }

}
