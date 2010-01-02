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
 *   DiscoveryListenerSpec.scala
 *
 */

package org.pomelo.server

import scala.actors.Actor

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

import java.net.DatagramPacket

import message._

object ResponseTestActor extends Actor {
    var message : Message = null
    def act() = {
        receive {
            case m: Message => message = m
            case _ => exit()
        }
    }
}

class DiscoveryListenerSpec extends Spec with ShouldMatchers{

    describe("The DiscoveryListener actor") {

        val listener = new DiscoveryListener(ResponseTestActor)
        
        describe("when receiving a valid discovery query packet") {

            val id = 12345
            val data = (new DiscoveryQuery(id, "client string")).encode().toArray[Byte]

            ResponseTestActor.start
            listener.start
            listener ! (new DatagramPacket(data, data.length))
            Thread.sleep(100)
            ResponseTestActor ! "stop"
            listener ! "stop"
            
            it ("should forward a valid DiscoveryResponse to the response actor") {
                ResponseTestActor.message.asInstanceOf[DiscoveryResponse] should not equal(null)
            }

            it ("should forward a DiscoveryResponse with id " + id) {
                ResponseTestActor.message.id should equal(id)
            }
        }
    }
}
