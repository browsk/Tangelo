/**
 *   Copyright 2009 Brett Carswell
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 *   MountPoint.scala
 *
 */

package org.pomelo.filesystem

class Directory(name: String, physicalPath: String) extends DirectoryItem(name, ItemAttribute.Directory) {
    var path = physicalPath
    private var children: List[DirectoryItem]= null

    override def hashCode = physicalPath.hashCode

    override def equals(other: Any) = other match {
        case that: Directory => (that canEqual this) && 
            (this.path == that.path) &&
            (this.name == that.name)
        case _ =>false
    }

    def canEqual(other: Any) = other.isInstanceOf[Directory]

    def getChildren(): Seq[DirectoryItem] = {
        if (children == null) {

            children = List[DirectoryItem]()

            def addChild(file: java.io.File) {
                println("Adding " + file.getName)
                if (file.isDirectory) {
                    children = children ::: List(new Directory(file.getName, file.getCanonicalPath))
                }
                else {
                    children = children ::: List(new File(file.getName, file.getCanonicalPath, file.length.toInt))
                }
            }
            val files = (new java.io.File(physicalPath)).listFiles

            println(files.length + " files")
            files.foreach(f => addChild(f))
        }

        children
    }

}
