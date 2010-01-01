package org.pomelotest.message

import org.pomelo.message.MessageDecoder
import scala.collection.mutable.ArrayBuffer
import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

class MessageDecoderSpec extends Spec with ShouldMatchers {

    describe("A message decoder") {

        describe("when initialised with a buffer containing a single int") {
            val data : List[Byte] = List(0x00, 0x00, 0x12, (0xfe).asInstanceOf[Byte])
            val buffer = new ArrayBuffer[Byte]
            buffer.appendAll(data)

            val decoder = new MessageDecoder(buffer)

            it ("should contain an int of value 4862") {
                val value = decoder.getInt()

                value should equal (4862)
            }
        }

        describe("when initialised with a buffer containing a consecutive ints ") {
            val data : List[Byte] = List(0x00, 0x00, 0x12, (0xfe).asInstanceOf[Byte], (0x80).asInstanceOf[Byte], 0x00, 0x00, 0x01)
            val buffer = new ArrayBuffer[Byte]
            buffer.appendAll(data)

            val decoder = new MessageDecoder(buffer)

            it ("should contain an int of value 4862 followed by an int of value -2147483647") {
                decoder.getInt() should equal (4862)
                decoder.getInt() should equal (-2147483647)
            }
        }

        describe("when initialised with a string 'hello'") {
            val data : List[Byte] = List(0x00, 0x00, 0x00, 0x05, 'h', 'e', 'l', 'l', 'o')
            val buffer = new ArrayBuffer[Byte]
            buffer.appendAll(data)

            val decoder = new MessageDecoder(buffer)

            it ("should decode the string 'hello'") {
                decoder.getString() should equal ("hello")
            }
        }

        describe("when initialised with an empty string") {
            val data : List[Byte] = List(0x00, 0x00, 0x00, 0x00)
            val buffer = new ArrayBuffer[Byte]
            buffer.appendAll(data)

            val decoder = new MessageDecoder(buffer)

            it ("should decode the string \"\"") {
                decoder.getString() should equal ("")
            }
        }

        describe("when initialised with a string 'hello' followed by the int 123") {
            val data : List[Byte] = List(0x00, 0x00, 0x00, 0x05, 'h', 'e', 'l', 'l', 'o', 0x00, 0x00, 0x00, 123)
            val buffer = new ArrayBuffer[Byte]
            buffer.appendAll(data)

            val decoder = new MessageDecoder(buffer)

            it ("should decode the string 'hello' followed by 123") {
                decoder.getString() should equal ("hello")
                decoder.getInt() should equal (123)
            }
        }

        describe("when initialised with a single byte 0x01") {
            val data : List[Byte] = List(0x01)
            val buffer = new ArrayBuffer[Byte]
            buffer.appendAll(data)

            val decoder = new MessageDecoder(buffer)

            it ("should decode the byte 0x01") {
                decoder.getByte() should equal(0x01)
            }
        }

        describe("when initialised with a byte sequence") {
            val data : List[Byte] = List(0x01, (0xaa).asInstanceOf[Byte], 0x55)
            val buffer = new ArrayBuffer[Byte]
            buffer.appendAll(data)

            val decoder = new MessageDecoder(buffer)

            it ("should decode the correct byte sequence") {
                decoder.getByte() should equal(0x01)
                decoder.getByte() should equal((0xaa).asInstanceOf[Byte])
                decoder.getByte() should equal(0x55)
            }
        }
    }
}
