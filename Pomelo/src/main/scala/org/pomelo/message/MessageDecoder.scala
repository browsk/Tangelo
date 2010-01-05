package org.pomelo.message

import scala.collection.mutable.Buffer
import scala.StringBuilder

class MessageDecoder(data : Seq[Byte]) {
    val buffer = data

    val it : Iterator[Byte] = data.elements

    def getString() : String = {
        val stringLength = getInt()
        val string = new StringBuilder
        it.take(stringLength).foreach( c => string.append(c.asInstanceOf[Char]) )

        string.toString
    }

    def getInt() : Int = {
        it.take(4).foldLeft(0)(
            (result, current) =>
                (result << 8) + (current.asInstanceOf[Short] & 0x00ff)
        )
    }

    def getByte() : Byte = it.next()
}
