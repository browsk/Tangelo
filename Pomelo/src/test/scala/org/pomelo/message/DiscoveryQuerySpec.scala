/*
 * DiscoveryQuerySpec.scala
 *
 */

package org.pomelo.message

import scala.collection.mutable.ArrayBuffer

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

/**@RunWith(classOf[JUnitRunner])*/
class DiscoveryQuerySpec  extends Spec with ShouldMatchers {
    val clientVersion = "Client version string"

    describe("A discovery query message") {
        describe("when constructed with a buffer containing a valid message") {
            val data = (new MessageSerializer(new DiscoveryQuery(345, clientVersion))).encode

            val buffer = new ArrayBuffer[Byte]
            buffer.appendAll(data)

            val message = DiscoveryQuery(buffer)
            
            it ("should have a message id of 345") {
                message.id should equal (345)
            }

            it ("should have a length of 34") {
                message.length should equal(34)
            }

            it ("should have a payload length of 30") {
                message.payloadLength should equal(30)
            }

            it ("should have a client version string of " + clientVersion) {
                message.clientVersion should equal(clientVersion)
            }
        }
        describe("constructed with client version '" + clientVersion + "'") {
            val id = 123
            val message = new DiscoveryQuery(id, clientVersion)
            val expectedLength = Message.headerLength + Message.encodedStringLength(clientVersion)

            it("should be " + expectedLength + " bytes in length") {
                message.length should equal (expectedLength)
            }

            it ("should have the specified client version string of '" + clientVersion + "'") {
                message.clientVersion should equal (clientVersion)
            }

            it ("should have an id of " + id) {
                message.id should equal (id)
            }

            it ("should have a type of 90") {
                message.msgType should equal (90)
            }

            it ("should encode to a buffer of length " + expectedLength) {
                val buffer = (new MessageSerializer(message)).encode
                buffer.length should equal (expectedLength)
            }

            describe ("that is encoded to a buffer") {
                val buffer = (new MessageSerializer(message)).encode

                it ("should have the correct message length")
                {
                    buffer(0) should equal(0)
                    buffer(1) should equal(0)
                    buffer(2) should equal(0)
                    buffer(3) should equal(message.payloadLength)
                }

                it ("should have the correct type") {
                    buffer(4) should equal(90)
                }

                it ("should have the correct message id") {
                    buffer(5) should equal(0)
                    buffer(6) should equal(0)
                    buffer(7) should equal(0)
                    buffer(8) should equal(123)
                }

                it ("should have the correct string length encoded") {
                    buffer(9) should equal(0)
                    buffer(10) should equal(0)
                    buffer(11) should equal(0)
                    buffer(12) should equal(message.clientVersion.length)
                }


                it ("should have the correct string in the buffer") {
                    buffer.trimStart(13)

                    val string = new StringBuilder
                    buffer.foreach(b => string.append(b.asInstanceOf[Char]))

                    string.toString should equal(message.clientVersion)
                }
                
            }
        }

        describe("with an empty client version string") {
            val id = 123
            val message = new DiscoveryQuery(id, "")
            val expectedLength = Message.headerLength + Message.encodedStringLength("")

            it ("should have a length of " + expectedLength) {
                message.length should equal (expectedLength)
            }

            it ("should have an empty client version string") {
                message.clientVersion should equal ("")
            }

            it ("should have an id of " + id) {
                message.id should equal (id)
            }

            it ("should have a type of 90") {
                message.msgType should equal (90)
            }
        }
    }
}
