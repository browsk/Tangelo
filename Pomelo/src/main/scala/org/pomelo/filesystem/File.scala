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
 *   class File.scala
 *
 */

package org.pomelo.filesystem

class File(name: String, fullPath: String, size: Int) extends DirectoryItem(name, ItemAttribute.File, size) {
    val path = fullPath
    
    def this(name: String, fullPath: String) = this(name, fullPath, -1)

    override def hashCode = fullPath.hashCode

    override def equals(other: Any) = other match {
        case that: Directory => (that canEqual this) && 
            (this.path == that.path) &&
            (this.name == that.name)
        case _ =>false
    }

    def canEqual(other: Any) = other.isInstanceOf[Directory]
}
