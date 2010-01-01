/*
 * MessageSpec.scala
 *
 */

package org.pomelotest.message

import org.pomelo.message.Message
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
}
