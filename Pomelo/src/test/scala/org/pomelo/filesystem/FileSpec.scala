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
 *   FileSpec.scala
 *
 */

package org.pomelo.filesystem

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

class FileSpec extends Spec with ShouldMatchers {
    describe("A File instance constructed without a size") {
        val file : File = new File("test.mp3")

        describe("when converted to XML") {
            val xml = file.toXML

            it("should have the correct name") {
                (xml \ "NAME").text should equal("test.mp3")
            }

            it("should have the correct attribute") {
                (xml \ "ATTRIB").text should equal("file")
            }

            it("should have a size of -1") {
                (xml \ "SIZE").text.toInt should equal(-1)
            }
        }
    }

    describe("A File instance constructed with a size of 1024") {
        val file : File = new File("test.mp3", 1024)

        describe("when converted to XML") {
            val xml = file.toXML

            it("should have the correct name") {
                (xml \ "NAME").text should equal("test.mp3")
            }

            it("should have the correct attribute") {
                (xml \ "ATTRIB").text should equal("file")
            }

            it("should have a size of 1024") {
                (xml \ "SIZE").text.toInt should equal(1024)
            }
        }
    }

}
