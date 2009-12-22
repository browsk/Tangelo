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
 *  Based in the main on the loggers from Lift
 *  http://github.com/dpp/liftweb/blob/338119b8d7a76adcb9f45e9aa8a2b946d9c81118/lift-util/src/main/scala/net/liftweb/util/Log.scala
 */

package org.pomelo.utils

import org.slf4j.LoggerFactory

object PomeloLoggerFactory {
    def getLogger(klass: Class[AnyRef]) : PomeloLogger = new Slf4jLogger(LoggerFactory.getLogger(klass))
    def getLogger(name: String) : PomeloLogger = new Slf4jLogger(LoggerFactory.getLogger(name))
}
