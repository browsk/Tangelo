/*
 * Discovery.scala
 *
 */

package org.pomelo.message

import scala.collection.mutable.ArrayBuffer

class DiscoveryQuery(id: Int, version : String) extends Message(90, id) {
    case class DiscoveryQueryType() extends AbstractMessageType(90)
    type MessageType = DiscoveryQueryType

    override val length = headerLength + version.length + 4

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
