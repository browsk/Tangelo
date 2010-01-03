/*
 * Null.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.pomelo.message

import scala.collection.mutable.Buffer

class Null(id: Int) extends Message(10, id) {
    
    override def length = Message.headerLength

    override def encode = {
        val encoder = new MessageEncoder

        Message.encodeHeader(this, encoder)

        encoder.buffer
    }
}

object Null {
    def apply(data: Buffer[Byte]) : Null = {
        val decoder = new MessageDecoder(data)

        val length = decoder.getInt
        assert(10 == decoder.getByte)
        val id = decoder.getInt

        new Null(id)
    }
}