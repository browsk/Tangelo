/*
 * DiscoveryResponse.scala
 *
 */

package org.pomelo.message

import org.pomelo.server.Settings

class DiscoveryResponse(query : DiscoveryQuery) extends Message(91, query.id)  with Serializable {
    abstract case class DiscoveryQueryType() extends MessageType(91)
    //type MessageType = DiscoveryQueryType

    override def length = Message.headerLength +
                Message.encodedStringLength(Settings.PORT) +
                Message.encodedStringLength(Settings.ADDRESS) +
                Message.encodedStringLength(Settings.COMMENT) +
                Message.encodedStringLength(Settings.VERSION)


    override def sequence() = {
        List[Any](payloadLength, msgType, id, Settings.ADDRESS, Settings.PORT, Settings.VERSION, Settings.COMMENT)
    }
}
