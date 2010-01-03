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
 *   NullCommandSpec.scala
 *
 */

package org.pomelo.command

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.mock.MockitoSugar

import org.mockito.Mockito._
import org.mockito.Matchers
import org.mockito.ArgumentCaptor

import io.MessageWriter
import message.Null
import message.Ok

class NullCommandSpec extends Spec with ShouldMatchers with MockitoSugar {

    describe("A NullCommand when executed") {

        val mockWriter = mock[MessageWriter]
        val message = new Null(213)
        val command = new NullCommand(message, mockWriter)

        command.execute

        it("should write an OK message") {
            val argument: ArgumentCaptor[Ok] = ArgumentCaptor.forClass(classOf[Ok])
            verify(mockWriter).write(argument.capture())
            argument.getValue.id should equal (213)
        }
    }
}
