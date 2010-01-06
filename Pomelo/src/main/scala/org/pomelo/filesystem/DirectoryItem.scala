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
 *   Directory.scala
 *
 */

package org.pomelo.filesystem

abstract class DirectoryItem(itemName: String, itemAttribute: ItemAttribute.Value, itemSize: Int) {

    val name = itemName
    val attribute = itemAttribute
    val size = itemSize
    
    def this(name: String, attribute: ItemAttribute.Value) = this(name, attribute, -1)

    def toXML =
        <DIRECTORYITEM>
            <NAME>{name}</NAME>
            <ATTRIB>{attribute}</ATTRIB>
            <SIZE>{size}</SIZE>
        </DIRECTORYITEM>
}
