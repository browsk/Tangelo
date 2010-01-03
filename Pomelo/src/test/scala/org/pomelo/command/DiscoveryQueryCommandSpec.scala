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
 *   DiscoveryQueryCommand.scala
 *
 */

package org.pomelo.command

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.mock._

import org.mockito.Mockito._
import org.mockito.Matchers

import io.MessageWriter
import message.DiscoveryQuery
import message.DiscoveryResponse

class DiscoveryQueryCommandSpec extends Spec with ShouldMatchers with MockitoSugar {
    describe("A DiscoveryQueryCommand when executed") {

        val mockWriter = mock[MessageWriter]
        val query = new DiscoveryQuery(123, "client version")
        val command = new DiscoveryQueryCommand(query, mockWriter)

        it("should write a discovery response to the MessageWriter") {

            command.execute

            verify(mockWriter).write(Matchers.isA(classOf[DiscoveryResponse]))
        }
    }
}
