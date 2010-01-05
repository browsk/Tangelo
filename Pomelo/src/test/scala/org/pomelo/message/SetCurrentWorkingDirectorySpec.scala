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
 *   SetCurrentWorkingDirectorySpec.scala
 *
 */

package org.pomelo.message

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

class SetCurrentWorkingDirectorySpec extends Spec with ShouldMatchers {

    describe("A SetCurrentWorkingDirectory message when deserialised from a buffer") {
        val data : List[Byte] = List(0x00, 0x00, 0x00, 0x0a, 0x0b, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x01, '.'.asInstanceOf[Byte])

        val message = SetCurrentWorkingDirectory(data)
        
        it("should have an id of 12 ") {
            message.id should equal(256)
        }

        it("should have a directoryName of '.'") {
            message.directoryName should equal(".")
        }
    }
}
