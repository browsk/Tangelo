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
 *   SetCurrentWorkingDirectory.scala
 *
 */

package org.pomelo.message

import scala.collection.mutable.Buffer

class SetCurrentWorkingDirectory(id: Int, directory: String) extends Message(11, id) {
    val directoryName = directory
    
    def length = Message.headerLength + Message.encodedStringLength(directoryName)
}

object SetCurrentWorkingDirectory {
    def apply(buffer: Seq[Byte]) : SetCurrentWorkingDirectory = {
        val decoder = new MessageDecoder(buffer)

        val length = decoder.getInt
        assert(11 == decoder.getByte)

        val id = decoder.getInt
        val directoryName = decoder.getString

        new SetCurrentWorkingDirectory(id, directoryName)
    }
}
