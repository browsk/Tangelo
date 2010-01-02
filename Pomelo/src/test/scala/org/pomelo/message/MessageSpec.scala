/*
 * MessageSpec.scala
 *
 */

package org.pomelo.message

import scala.collection.mutable.Buffer

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

class MessageSpec extends Spec with ShouldMatchers {

    describe ("the encodedStringLength method") {
        it ("should return 4 for an empty string") {
            Message.encodedStringLength("") should equal (4)
        }

        val testString = "testing 123"
        it ("should return length " + (4 + testString.length) + " for " + testString) {
            Message.encodedStringLength(testString) should equal (4 + testString.length)
        }

    }

    describe ("the fromBuffer method when called with a buffer containing a DiscoveryQuery message") {

        val id = 123
        val clientVersion = "client version string"
        
        val buffer: Buffer[Byte] = (new DiscoveryQuery(id, clientVersion)).encode()

        val mesg = Message.fromBuffer(buffer).asInstanceOf[DiscoveryQuery]
        
        it ("should return a DiscoveryQuery instance") {

            mesg should not equal(null)
        }

        it ("should return a DiscoveryQuery instance with the correct message id") {
            mesg.id should equal(id)
        }

        it ("should return a DiscoveryQuery instance with the correct client version string") {
            mesg.clientVersion should equal(clientVersion)
        }

    }
}
