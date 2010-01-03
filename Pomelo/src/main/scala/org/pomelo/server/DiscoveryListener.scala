package org.pomelo.server

import scala.actors.Actor
import scala.collection.mutable.ArrayBuffer

import java.net.DatagramPacket
import java.net.DatagramSocket

import utils.PomeloLoggerFactory
import message._
import command._
import io._

class DiscoveryListener(responseService : Actor) extends Actor {

    def act() {
        val logger = PomeloLoggerFactory.getLoggerForName("pomelo")

        while (true) {
            receive {
                case packet : DatagramPacket =>

                    val packetLength = packet.getLength
                    logger.debug("Listener got packet " + packetLength)

                    if (packetLength >= 9) {

                        val data: ArrayBuffer[Byte] = new ArrayBuffer[Byte]()
                        
                        packet.getData().copyToBuffer(data)

                        val message = Message.fromBuffer(data)

                        if(message.getClass() == classOf[DiscoveryQuery])
                        {
                            logger.debug("Sending response")

                            val writer = new UdpMessageWriter(packet.getSocketAddress)

                            responseService ! new DiscoveryQueryCommand(message.asInstanceOf[DiscoveryQuery], writer)
                        }
                        else {
                            logger.info("Got an unknown message of type " + data(4))
                        }
                    }
                    else {
                        logger.info("Packet too short")
                    }
                    act()

                case msg => logger.info("unhandled message " + msg)
                    exit()
            }

        }
    }
}
