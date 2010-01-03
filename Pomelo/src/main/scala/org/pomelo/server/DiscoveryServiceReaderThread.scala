/*
 * DiscoveryServiceReaderThread.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.pomelo.server

import java.net._
import java.io.IOException
import scala.actors.Actor
import org.pomelo.utils.PomeloLoggerFactory

class DiscoveryServiceReaderThread(listener : Actor, port : Int)  extends Thread {
    private val socket = new DatagramSocket(null)
    socket.setReuseAddress(true)
    socket.bind(new InetSocketAddress(InetAddress.getLocalHost, port))
    private var forceExit = false

    def term() {
        forceExit = true
        socket.close
    }

    override def run() {

        val logger = PomeloLoggerFactory.getLoggerForName("pomelo")

        logger.info("Discovery service listening on port " + port)

        while (!forceExit) {
            val buf: Array[byte] = new Array[byte](256);
            val packet = new DatagramPacket(buf, buf.length);

            try {
                socket.receive(packet);

                logger.info("Discovery service reader rxed data from " + packet.getSocketAddress)

                logger.info(buf.toString)

                listener ! packet
            } catch {

                case ioe: IOException => {
                    if (!forceExit) logger.error(ioe)
                }
                case e: Exception => {
                    forceExit = true
                    logger.error(e)
                }
            }
        }
        logger.info("DiscoveryServiceReaderThread exiting")

        if (!socket.isClosed) socket.close
    }

}
