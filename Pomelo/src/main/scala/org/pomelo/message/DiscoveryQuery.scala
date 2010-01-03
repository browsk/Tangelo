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

class DiscoveryQuery(id: Int, version : String) extends Message(90, id) with Serializable {

    //type MessageType = DiscoveryQueryType

    override val length = Message.headerLength + version.length + 4

    val clientVersion = version

    override def sequence() = {
        List[Any](payloadLength, msgType, id, clientVersion)
    }

    override def toString = {
        "id:" + id + " version:" + clientVersion
    }
}
