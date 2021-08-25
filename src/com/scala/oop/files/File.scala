package com.scala.oop.files

import com.scala.oop.filesystem.FileSystemException

class File(override val parentPath: String, override val name: String, val contents: String) extends  DirEntry(parentPath, name){

  def asDirectory: Directory =
    throw new FileSystemException("A file cannot be converted to a directory")

  def getType: String = "File"
  def isDirectory: Boolean = false
  def isFile: Boolean = true
  def asFile: File = this


  def setContents(newContents: String): File =
    new File(parentPath, name, newContents)

  def appendContents(newContents: String): File =
    setContents(contents + "\n" + newContents)
}

object File {
  def empty(parentPath: String, name: String): File =
    new File(parentPath, name, "")
}