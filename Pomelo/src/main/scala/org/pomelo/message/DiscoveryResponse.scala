/*
 * DiscoveryResponse.scala
 *
 */

package org.pomelo.message

import org.pomelo.server.Settings

class DiscoveryResponse(query : DiscoveryQuery) extends Message(91, query.id) {
    abstract case class DiscoveryQueryType() extends AbstractMessageType(91)
    type MessageType = DiscoveryQueryType

    override def length = headerLength
            + Message.encodedStringLength(Settings.PORT)
            + Message.encodedStringLength(Settings.ADDRESS)
            + Message.encodedStringLength(Settings.COMMENT)
            + Message.encodedStringLength(Settings.VERSION)
            
    override def encode() = {
    
        val encoder = new MessageEncoder
        encoder.append(length)

        encoder.buffer
    }
}
