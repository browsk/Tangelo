/*
 * Type.scala
 *
 */

package org.pomelo.message

case class MessageType(val id: Byte) {
  val messageType = id
}

case class DiscoveryQueryType() extends MessageType(90)


