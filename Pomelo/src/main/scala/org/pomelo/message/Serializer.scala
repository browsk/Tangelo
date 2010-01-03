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
 *   Serializer.scala
 *
 */

package org.pomelo.message

import scala.collection.mutable.ArrayBuffer

class Serializer {
    val buffer = new ArrayBuffer[byte]

    def append(data : Any) : Unit = {
        data match {
            case i: Int =>
                buffer += (i >> 24).toByte
                buffer += (i >> 16).toByte
                buffer += (i >> 8).toByte
                buffer += i.toByte
            case s: String =>
                append(s.length)
                buffer ++= s.map( c => c.toByte )
            case b: Byte =>
                buffer += b
            case x: AnyRef =>
                val klass = x.getClass()
                throw new IllegalArgumentException("can't encode data of type " + klass)
            case x: Any =>
                throw new IllegalArgumentException("can't encode data : " + x)
        }
    }
}
