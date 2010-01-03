/*
 * DiscoveryServer.scala
 *
 */

package org.pomelo.server

import scala.actors.Actor

import utils._
import command.Command

object DiscoveryService extends Actor {
    def act() {
        val logger = PomeloLoggerFactory.getLoggerForName("pomelo")

        logger.info("This is the discovery service actor")
        val listener = new DiscoveryListener(this)
        listener.start
        
        val reader = new DiscoveryServiceReaderThread(listener, Settings.PORT.toInt)
        reader.start

        var exit = false
        while (!exit) {
            receive {
                case command : Command => command.execute
                    
                case msg =>
                    logger.error("unknown message " + msg)
                    reader.term
                    listener ! "stop"
                    reader.join(200)
                    exit = true;
            }
        }
        logger.info("Exiting DiscoveryService")
    }
}
