/*
 * MessageEncoder.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.pomelo.message

import scala.collection.mutable.ArrayBuffer

class MessageEncoder {
    val buffer = new ArrayBuffer[byte]

    def append(data : Any) = {
        data match {
            case i: Int =>
                buffer += (i >> 24).toByte
                buffer += (i >> 16).toByte
                buffer += (i >> 8).toByte
                buffer += i.toByte
        }
    }

    
}
