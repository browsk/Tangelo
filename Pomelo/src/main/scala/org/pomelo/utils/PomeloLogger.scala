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

trait PomeloLogger {
  def isTraceEnabled: Boolean = false
  def trace(msg: => AnyRef): Unit = ()
  def trace(msg: => AnyRef, t: => Throwable): Unit = ()

  def assertLog(assertion: Boolean, msg: => String): Unit = ()

  def isDebugEnabled: Boolean = false
  def debug(msg: => AnyRef): Unit = ()
  def debug(msg: => AnyRef, t: => Throwable): Unit = ()

  def isErrorEnabled: Boolean = false
  def error(msg: => AnyRef): Unit = ()
  def error(msg: => AnyRef, t: => Throwable): Unit = ()

  def fatal(msg: AnyRef): Unit = ()
  def fatal(msg: AnyRef, t: Throwable): Unit = ()

  def level: LogLevels.Value = LogLevels.Off
  def level_=(level: LogLevels.Value): Unit = ()
  def name: String = "Null"

  def isInfoEnabled: Boolean = false
  def info(msg: => AnyRef): Unit = ()
  def info(msg: => AnyRef, t: => Throwable): Unit = ()

  def isEnabledFor(level: LogLevels.Value): Boolean = false

  def isWarnEnabled: Boolean = false
  def warn(msg: => AnyRef): Unit = ()
  def warn(msg: => AnyRef, t: => Throwable): Unit = ()
}

object LogLevels extends Enumeration {
  val All = Value(1, "All")
  val Trace = Value(3, "Trace")
  val Debug = Value(5, "Debug")
  val Warn = Value(7, "Warn")
  val Error = Value(9, "Error")
  val Fatal = Value(11, "Fatal")
  val Info = Value(13, "Info")
  val Off = Value(15, "Off")
}