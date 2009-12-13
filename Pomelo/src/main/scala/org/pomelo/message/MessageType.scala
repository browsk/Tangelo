/*
 * Type.scala
 *
 */

package org.pomelo.message

abstract case class AbstractMessageType(val id: Byte) {
  val messageType = id
}


