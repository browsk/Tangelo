/*
 * Message.scala
 *
 */

package org.pomelo.message

import scala.collection.mutable.ArrayBuffer

trait MessageProperties {
  val headerLength = 9
}

/**
 * Abstract class to represent a message
 *
 * @param   mesgType      the message type byte from the xbmsp doucment
 * @param   messageId     the unique message id
 *
 */
abstract class Message(mesgType : Byte, messageId : Int) {
  type MessageType <: AbstractMessageType

  /**
   * the length of the message in bytes once the message has been encoded
   */
  def length : Int

  /**
   * the 8bit message type identifier from the xbmsp
   */
  val msgType : Byte = mesgType

  /**
   * unique message id
   */
  val id : Int = messageId

  /**
   * encode the message into an ArrayBuffer
   */
  def encode() : ArrayBuffer[Byte]
}

object Message extends MessageProperties {
    def encodedStringLength(string : String ) = 4 + string.length

    //def encodeIntToPosition()
}