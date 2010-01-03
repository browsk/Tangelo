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
 *   NullSpec.scala
 *
 */

package org.pomelo.message

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

class NullSpec extends Spec with ShouldMatchers {

    describe("A null message constructed with id 23") {
        val message = new Null(23)
        it("should have an id of 23") {
            message.id should equal(23)
        }
    }

    describe("A null message when encoded") {

        val buffer = (new MessageSerializer(new Null(123))).encode

        it("should result in a buffer length of 9") {
            buffer.length should equal(9)
        }

        it("should contain the correct length in the buffer") {
            buffer(0) should equal(0)
            buffer(1) should equal(0)
            buffer(2) should equal(0)
            buffer(3) should equal(5)
        }

        it("should contain the correct message id byte in the buffer") {
            buffer(4) should equal(10)
        }

        it("should contain the correct id in the buffer") {
            buffer(5) should equal(0)
            buffer(6) should equal(0)
            buffer(7) should equal(0)
            buffer(8) should equal(123)
        }


    }
}
