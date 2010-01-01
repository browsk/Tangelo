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
 * DiscoveryServiceReaderSpec.scala
 *
 */

package org.pomelotest.server

import scala.actors.Actor
import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import org.pomelo.server.DiscoveryServiceReaderThread
import java.net._
import org.pomelo.utils._


object TestListener extends Actor {
    var datagram : DatagramPacket = null

    var logger = PomeloLoggerFactory.getLoggerForName("testing")
    def act = {
        logger.info("in testlistener")

        receive {
            case x : DatagramPacket => {
                    logger.info("Got a datagram")
                    datagram = x
                }
            case a : Any => {
                    logger.info("exiting")
                }
        }
    }
}

class DiscoveryServiceReaderSpec extends Spec with ShouldMatchers {
    var logger = PomeloLoggerFactory.getLoggerForName("testing")

    describe("A DiscoveryServiceReader") {
        it("should forward all datagrams to the specified listener") {

            val testPort = 50723

            logger.info("in test ####")
            val reader = new DiscoveryServiceReaderThread(TestListener, testPort)

            TestListener.start
            reader.start

            val payload = "hello".getBytes("ASCII")
            val packet = new DatagramPacket(payload, payload.length, InetAddress.getLocalHost(), testPort)

            (new DatagramSocket).send(packet)

            Thread.sleep(200)

            reader.term
            reader.join(200)

            TestListener.datagram.getLength() should equal (5)

        }
    }
}
