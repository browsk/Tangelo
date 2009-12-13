/*
 * Message.scala
 *
 */

package org.pomelo.message

trait MessageProperties {
  val headerLength = 9
}

abstract class Message(mesgType : Byte, messageId : Int) extends MessageProperties{
  type MessageType <: AbstractMessageType

  val id : Int = messageId
  val msgType : Byte = mesgType
  def length : Int
  
  def encode() : Array[Byte]
}

object Message {
    def encodedStringLength(string : String ) = 4 + string.length

    //def encodeIntToPosition()
}