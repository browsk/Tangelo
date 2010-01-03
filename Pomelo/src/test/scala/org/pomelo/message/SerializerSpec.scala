/**
 *   Copyright 2009 Brett Carswell
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 *   SerializerSpec.scala
 *
 */

package org.pomelo.message

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

class SerializerSpec extends Spec with ShouldMatchers {
    describe("A Serializer") {
        describe("appending a single integer (0)") {
            val encoder = new Serializer
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

        describe("appending a single integer : " + testDataA) {
            val encoder = new Serializer
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

        describe("appending two consecutive integers : " + testDataA + "," + testDataB) {
            val encoder = new Serializer
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

        describe("appending the string '" + string + "'") {
            val expectedLength = 4 + string.length
            val encoder = new Serializer
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

        describe("appending a string followed by an int") {
            val expectedLength = 4 + string.length + 4
            val encoder = new Serializer
            encoder.append(string)
            encoder.append(10)

            it ("should result in a buffer length of " + expectedLength) {
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
                encoder.buffer(8) should equal (0)
                encoder.buffer(9) should equal (0)
                encoder.buffer(10) should equal (0)
                encoder.buffer(11) should equal (10)

            }
        }

        describe("appending a byte") {
            val encoder = new Serializer
            val b : Byte = 100
            encoder.append(b)
            it ("should result in a buffer length of 1") {
                encoder.buffer.length should equal (1)
            }

            it ("should result in the correct byte being in the buffer") {
                encoder.buffer(0) should equal (100)
            }
        }

        describe("appending a byte followed by an int") {
            val encoder = new Serializer
            val b : Byte = 10
            val i : Int = 13
            encoder.append(b)
            encoder.append(i)
            it ("should result in a buffer length of 5") {
                encoder.buffer.length should equal (5)
            }

            it ("should result in the correct byte sequence being in the buffer") {
                encoder.buffer(0) should equal (10)
                encoder.buffer(1) should equal (0)
                encoder.buffer(2) should equal (0)
                encoder.buffer(3) should equal (0)
                encoder.buffer(4) should equal (13)
            }
        }

        describe("appending an empty string") {
            val encoder = new Serializer

            encoder.append("")

            it ("should result in a buffer length of 4") {
                encoder.buffer.length should equal (4)
            }

            it ("should result in the correct byte sequence being in the buffer") {
                encoder.buffer(0) should equal (0)
                encoder.buffer(1) should equal (0)
                encoder.buffer(2) should equal (0)
                encoder.buffer(3) should equal (0)
            }
        }

        describe("appending an unsupported reference type") {
            it ("should throw an IllegalArgumentException") {
                val encoder = new Serializer

                evaluating {encoder.append(encoder)} should produce [IllegalArgumentException]
            }
        }

        describe("appending an unsupported value type") {
            it ("should throw an IllegalArgumentException") {
                val encoder = new Serializer
                val double : Double = 0.0
                evaluating {encoder.append(double)} should produce [IllegalArgumentException]
            }
        }
    }

}
