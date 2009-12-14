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

    val testDataA = 0x01234567
    val testDataB = 0xdeadbeef

    describe("encoding a single integer : " + testDataA) {
        val encoder = new MessageEncoder
        encoder.append(testDataA)

        it ("should result in a buffer of length 4") {
            encoder.buffer.length should equal (4)
        }

        it ("should contain the correct byte sequence") {
            encoder.buffer(0) should equal (0x01)
            encoder.buffer(1) should equal (0x23)
            encoder.buffer(2) should equal (0x45)
            encoder.buffer(3) should equal (0x67)
        }
    }

    describe("encoding a two consecutive integers : " + testDataA + "," + testDataB) {
        val encoder = new MessageEncoder
        encoder.append(testDataA)
        encoder.append(testDataB)

        it ("should result in a buffer of length 8") {
            encoder.buffer.length should equal (8)
        }

        it ("should contain the correct byte sequence") {
            encoder.buffer(0) should equal (0x01.toByte)
            encoder.buffer(1) should equal (0x23.toByte)
            encoder.buffer(2) should equal (0x45.toByte)
            encoder.buffer(3) should equal (0x67.toByte)
            encoder.buffer(4) should equal (0xde.toByte)
            encoder.buffer(5) should equal (0xad.toByte)
            encoder.buffer(6) should equal (0xbe.toByte)
            encoder.buffer(7) should equal (0xef.toByte)
        }
    }

    val string = "test"

    describe("encoding the string '" + string + "'") {
        val expectedLength = 4 + string.length
        val encoder = new MessageEncoder
        encoder.append(string)

        it ("should result in a buffer of length " + expectedLength) {
            encoder.buffer.length should equal (expectedLength)
        }

        it ("should result in a buffer with the correct byte sequence") {
            encoder.buffer(0) should equal (0)
            encoder.buffer(1) should equal (0)
            encoder.buffer(2) should equal (0)
            encoder.buffer(3) should equal (4)
            encoder.buffer(4) should equal ('t')
            encoder.buffer(5) should equal ('e')
            encoder.buffer(6) should equal ('s')
            encoder.buffer(7) should equal ('t')
        }
    }


}
