/*
 * Main.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.pomelo

import org.pomelo.server._
import scala.Console

object Main {

    /**
     * @param args the command line arguments
     */
    def main(args: Array[String]) :Unit = {
        DiscoveryService.start

        DiscoveryService ! Console.readChar

        Thread.sleep(1000)
        Console.println("Exiting...")
    }
}
