/*
 * MessageSerializer.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.pomelo.message

import scala.collection.mutable.Buffer

class MessageSerializer(message: Serializable) extends Serializer {

    def encode() : Buffer[Byte] = {
        message.sequence.foreach(item => append(item))
        buffer
    }
}
