/*
 *
 */

package org.pomelo.message

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

class TestClass extends Serializable {
    def packSequence() = List[Any](22.asInstanceOf[Byte], 43, "hello")
}

class MessageSerializerSpec extends Spec with ShouldMatchers {

    describe("A MessageSerializer when constructed with a test object") {
        val serializer = new MessageSerializer(new TestClass)

        val buffer = serializer.encode

        it("should serialize to a buffer of length 14") {
            buffer should have length(14)
        }

        it("should serialize to a buffer a byte valued 22 at position 0") {
            buffer(0) should equal(22)
        }

        it("should serialize an integer of 43 starting at position 1") {
            buffer(1) should equal(0)
            buffer(2) should equal(0)
            buffer(3) should equal(0)
            buffer(4) should equal(43)
        }

        it("should serialize the string 'hello' starting at position 5") {
            buffer(5) should equal(0)
            buffer(6) should equal(0)
            buffer(7) should equal(0)
            buffer(8) should equal(5)
            buffer(9) should equal('h')
            buffer(10) should equal('e')
            buffer(11) should equal('l')
            buffer(12) should equal('l')
            buffer(13) should equal('o')
        }

    }
}