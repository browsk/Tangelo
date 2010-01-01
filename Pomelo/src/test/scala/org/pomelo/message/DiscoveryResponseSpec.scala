/*
 * DiscoveryResponseSpec.scala
 *
 */

package org.pomelo.message

import org.pomelo.message.DiscoveryResponse
import org.pomelo.message.DiscoveryQuery
import org.pomelo.message.Message
import org.pomelo.server.Settings
import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

class DiscoveryResponseSpec extends Spec with ShouldMatchers{

    val discoveryQuery = new DiscoveryQuery(255, "Test Client")
    val discoveryResponse = new DiscoveryResponse(discoveryQuery)
    val expectedLength = Message.headerLength +
                            Message.encodedStringLength(Settings.ADDRESS) +
                            Message.encodedStringLength(Settings.PORT) +
                            Message.encodedStringLength(Settings.VERSION) + 
                            Message.encodedStringLength(Settings.COMMENT)


    describe ("A DiscoveryResponse message") {

        it ("should have a message type of 91") {
            discoveryResponse.msgType should equal (91)
        }

        it ("should have a message id of 255") {
            discoveryResponse.id should equal (255)
        }

        it ("should have a length of " + expectedLength) {
            discoveryResponse.length should equal (expectedLength)
        }

        describe ("when encoded the buffer") {
            val buffer = discoveryResponse.encode
            it ("should have a length of " + expectedLength) {
                buffer should have length (expectedLength)
            }

            it ("should have the correct encoded message length") {
                buffer(0) should equal(0)
                buffer(1) should equal(0)
                buffer(2) should equal(0)
                buffer(3) should equal(expectedLength)
            }

            it ("should have the correct type") {
                buffer(4) should equal(91)
            }

            it ("should have the correct message id") {
                buffer(5) should equal(0)
                buffer(6) should equal(0)
                buffer(7) should equal(0)
                buffer(8).asInstanceOf[Short] & 0x00ff should equal(255)
            }

            
        }
    }
}
