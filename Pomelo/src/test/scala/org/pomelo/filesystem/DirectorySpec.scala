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
 *   Directory.scala
 *
 */

package org.pomelo.filesystem

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

class DirectorySpec extends Spec with ShouldMatchers {
    describe("A Directory instance") {

        describe("when converted to XML") {
            val dir : Directory = new Directory("test", "c:\\temp")
            val xml = dir.toXML

            it("should have the correct name") {
                (xml \ "NAME").text should equal("test")
            }

            it("should have the correct attribute") {
                (xml \ "ATTRIB").text should equal("directory")
            }

            it("should have a size of -1") {
                (xml \ "SIZE").text.toInt should equal(-1)
            }
        }

        describe("when constructed for a populated directory") {
            val dir : Directory = new Directory("DirectorySpec", ".\\testdata\\DirectorySpec")

            val children = dir.getChildren

            it("should have a sequence with the 4 children") {
                children.length should equal(4)
            }

            it("should have a sequence containing a Directory called dir1") {
                children.exists(c => c.name == "dir1") should equal(true)
                val item = children.find(c => c.name == "dir1").getOrElse(null)

                item should not equal(null)
            }

            it("should have a sequence containing a Directory called dir2") {
                children.exists(c => c.name == "dir2") should equal(true)
                val item = children.find(c => c.name == "dir2").getOrElse(null)

                item should not equal(null)
            }

            it("should have a sequence containing a file called test.mp3") {
                children.exists(c => c.name == "test.mp3") should equal(true)
                val item = children.find(c => c.name == "test.mp3").getOrElse(null)

                item should not equal(null)

                item.size should equal(5)
            }

            it("should have a sequence containing a file called test.mp3 with a size of 5") {
                val item = children.find(c => c.name == "test.mp3").getOrElse(null)

                item.size should equal(5)
            }

            it("should have a sequence containing a file called test.avi") {
                children.exists(c => c.name == "test.avi") should equal(true)
                val item = children.find(c => c.name == "test.avi").getOrElse(null)

                item should not equal(null)

                item.size should equal(0)
            }

            it("should have a sequence containing a file called test.avi with a size of 0") {
                val item = children.find(c => c.name == "test.avi").getOrElse(null)

                item.size should equal(0)
            }
        }
    }
}
