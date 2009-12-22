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

import org.slf4j.Logger

class Slf4jLogger(val logger : Logger) extends PomeloLogger {

    override def isTraceEnabled: Boolean = logger.isTraceEnabled
    override def trace(msg: => AnyRef): Unit = if (isTraceEnabled) logger.trace(String.valueOf(msg))
    override def trace(msg: => AnyRef, t: => Throwable): Unit = if (isTraceEnabled) logger.trace(String.valueOf(msg), t)

    override def assertLog(assertion: Boolean, msg: => String): Unit = if (assertion) info(msg)

    override def isDebugEnabled: Boolean = logger.isDebugEnabled
    override def debug(msg: => AnyRef): Unit = if (isDebugEnabled) logger.debug(String.valueOf(msg))
    override def debug(msg: => AnyRef, t: => Throwable): Unit = if (isDebugEnabled) logger.debug(String.valueOf(msg), t)

    override def isErrorEnabled: Boolean = logger.isErrorEnabled
    override def error(msg: => AnyRef): Unit = if (isErrorEnabled) logger.error(String.valueOf(msg))
    override def error(msg: => AnyRef, t: => Throwable): Unit = if (isErrorEnabled) logger.error(String.valueOf(msg), t)

    override def fatal(msg: AnyRef): Unit = logger.error(String.valueOf(msg))
    override def fatal(msg: AnyRef, t: Throwable): Unit = logger.error(String.valueOf(msg), t)

    override def level: LogLevels.Value = LogLevels.All
    //override def level_=(level: LogLevels.Value): Unit =
    override def name: String = logger.getName

    override def isInfoEnabled: Boolean = logger.isInfoEnabled
    override def info(msg: => AnyRef): Unit = if (isInfoEnabled) logger.info(String.valueOf(msg))
    override def info(msg: => AnyRef, t: => Throwable): Unit = if (isInfoEnabled) logger.info(String.valueOf(msg), t)

    override def isEnabledFor(level: LogLevels.Value): Boolean = level match {
        case LogLevels.All => isTraceEnabled
        case LogLevels.Trace => isTraceEnabled
        case LogLevels.Info => isInfoEnabled
        case LogLevels.Warn => isWarnEnabled
        case LogLevels.Error => isErrorEnabled
        case LogLevels.Debug => isDebugEnabled
        case LogLevels.Fatal => isErrorEnabled
        case LogLevels.Off => !isErrorEnabled
    }

    override def isWarnEnabled: Boolean = logger.isWarnEnabled
    override def warn(msg: => AnyRef): Unit = if (isWarnEnabled) logger.warn(String.valueOf(msg))
    override def warn(msg: => AnyRef, t: => Throwable): Unit = if (isWarnEnabled) logger.warn(String.valueOf(msg), t)

}
