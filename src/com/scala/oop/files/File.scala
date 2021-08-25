package com.scala.oop.files

import com.scala.oop.filesystem.FileSystemException

class File(override val parentPath: String, override val name: String, contents: String) extends  DirEntry(parentPath, name){

  def asDirectory: Directory =
    throw new FileSystemException("A file cannot be converted to a directory")

  def getType: String = "File"

  def asFile: File = this
}

object File {
  def empty(parentPath: String, name: String): File =
    new File(parentPath, name, "")
}