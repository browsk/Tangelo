/*
 * Discovery.scala
 *
 */

package org.pomelo.message

import scala.collection.mutable.Buffer

object DiscoveryQuery {
    def apply(data: Buffer[Byte]) : DiscoveryQuery = {
        val decoder = new MessageDecoder(data)

        val length = decoder.getInt
        assert(90 == decoder.getByte)
        val id = decoder.getInt
        val clientVersion = decoder.getString

        new DiscoveryQuery(id, clientVersion)
    }
}

class DiscoveryQuery(id: Int, version : String) extends Message(90, id) {
    case class DiscoveryQueryType() extends AbstractMessageType(90)

    type MessageType = DiscoveryQueryType

    override val length = Message.headerLength + version.length + 4

    val clientVersion = version

    override def encode() = {
        val encoder = new MessageEncoder

        encoder.append(length)
        encoder.append(msgType)
        encoder.append(id)
        encoder.append(version)

        encoder.buffer
    }
}
