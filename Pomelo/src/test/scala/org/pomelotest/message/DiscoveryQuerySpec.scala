/*
 * DiscoveryQuerySpec.scala
 *
 */

package org.pomelotest.message

import org.pomelo.message.MessageProperties
import org.pomelo.message.Message
import org.pomelo.message.DiscoveryQuery
import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

/**@RunWith(classOf[JUnitRunner])*/
class DiscoveryQuerySpec  extends Spec with ShouldMatchers {
    val clientVersion = "Client version string"

    describe("A discovery query message from client version '" + clientVersion + "'") {
        val id = 123
        val message = new DiscoveryQuery(id, clientVersion)
        val expectedLength = message.headerLength + Message.encodedStringLength(clientVersion)
        
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
    }

    describe("A discovery message with an empty client version string") {
        val id = 123
        val message = new DiscoveryQuery(id, "")
        val expectedLength = message.headerLength + Message.encodedStringLength("")

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
