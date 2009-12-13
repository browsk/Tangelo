/*
 * MessageEncoder.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.pomelo.message

class MessageEncoder(message : Message) {
    val buffer = new Array[byte](message.length)
    private var index = 0


    def append(data : AnyType) = {
        case data : Int => println("int")
    }


}
