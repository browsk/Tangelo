/*
 * MessageEncoderSpec.scala
 *
 */

package org.pomelotest.message
import org.pomelo.message.MessageEncoder
import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

class MessageEncoderSpec extends Spec with ShouldMatchers {

    describe("encoding a single integer (0)") {
        val encoder = new MessageEncoder
        encoder.append(0)
        
        it ("should result in a buffer of length 4") {
            encoder.buffer.length should equal (4)
        }

        it ("should contain all zeros in the buffer") {
            encoder.buffer(0) should equal (0)
            encoder.buffer(1) should equal (0)
            encoder.buffer(2) should equal (0)
            encoder.buffer(3) should equal (0)
        }
    }
}
