package com.scala.oop.commands
import com.scala.oop.files.{DirEntry, Directory}
import com.scala.oop.filesystem.State

abstract class CreateEntry(name: String) extends Command {
  override def apply(state: State) = {
    val wd = state.wd
    if(wd.hasEntry(name)){
      state.setMessage("Entry " +  name + " already exists")
    } else if(name.contains(Directory.SEPARATOR)) {
      state.setMessage(name + " must not contains separators")
    } else if(checkIllegal(name)) {
      state.setMessage(name + ": illegal entry name")
    } else {
      doCreateEntry(name, state)
    }
  }

  def doCreateEntry(name: String, state: State): State = {
    def updateStructure(currentDirectory: Directory, path: List[String], newEntry: DirEntry): Directory = {
      if(path.isEmpty) currentDirectory.addEntry(newEntry)
      else {
        val oldEntry = currentDirectory.findEntry(path.head).asDirectory
        currentDirectory.replaceEntry(oldEntry.name, updateStructure(oldEntry, path.tail, newEntry))
      }
    }

    val wd = state.wd

    // 1. all the directories in the full path
    val allDirsInPath = wd.getAllFoldersInPath

    // 2. create new directory entry in the wd
    val newEntry: DirEntry = createSpecificEntry(state)

    // 3. update the whole directory structure starting from the root (is immutable)
    val newRoot = updateStructure(state.root, allDirsInPath, newEntry)

    // 4. find new working directory INSTANCE given wd's full path, in the NEW directory structure
    val newWd = newRoot.findDescendant(allDirsInPath)

    State(newRoot, newWd)
  }

  def checkIllegal(name: String): Boolean = {
    name.contains(".")
  }

  def createSpecificEntry(state: State): DirEntry
}
