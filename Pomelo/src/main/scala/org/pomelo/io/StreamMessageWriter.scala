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
 *   TcpMessageWriter.scala
 *
 */

package org.pomelo.io

import java.io.OutputStream
import message.MessageSerializer
import message.Serializable

class StreamMessageWriter(stream: OutputStream) extends MessageWriter {
    def write(message: Serializable) = {
        val data = (new MessageSerializer(message)).encode().toArray[Byte]
        stream.write(data)
    }
}
