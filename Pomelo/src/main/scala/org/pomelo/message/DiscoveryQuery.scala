/*
 * Discovery.scala
 *
 */

package org.pomelo.message

import scala.collection.mutable.ArrayBuffer

class DiscoveryQuery(id: Int, version : String) extends Message(90, id) {
    case class DiscoveryQueryType() extends AbstractMessageType(90)
    type MessageType = DiscoveryQueryType

    val clientVersion = version

    override val length = headerLength + version.length + 4

    override def encode() = {
        new ArrayBuffer[Byte]
    }
}
