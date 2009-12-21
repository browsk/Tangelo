/*
 * DiscoveryResponse.scala
 *
 */

package org.pomelo.message

import org.pomelo.server.Settings

class DiscoveryResponse(query : DiscoveryQuery) extends Message(91, query.id) {
    abstract case class DiscoveryQueryType() extends AbstractMessageType(91)
    type MessageType = DiscoveryQueryType

    override def length = Message.headerLength +
                Message.encodedStringLength(Settings.PORT) +
                Message.encodedStringLength(Settings.ADDRESS) +
                Message.encodedStringLength(Settings.COMMENT) +
                Message.encodedStringLength(Settings.VERSION)
            
    override def encode() = {
    
        val encoder = new MessageEncoder
        encoder.append(length)
        encoder.append(msgType)
        encoder.append(id)
        encoder.append(Settings.ADDRESS)
        encoder.append(Settings.PORT)
        encoder.append(Settings.VERSION)
        encoder.append(Settings.COMMENT)

        encoder.buffer
    }
}
