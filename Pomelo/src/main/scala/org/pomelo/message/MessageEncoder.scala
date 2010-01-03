/*
 * MessageEncoder.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.pomelo.message

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Buffer

class MessageEncoder(message: Serializable) {
    val buffer = new ArrayBuffer[byte]

    def encode() : Buffer[Byte] = {
        message.sequence.foreach(item => append(item))
        buffer
    }
    
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
