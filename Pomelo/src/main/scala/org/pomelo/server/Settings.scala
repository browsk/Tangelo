package org.pomelo.server

/**
 * represents the settings of the xbmsp server
 */
object Settings {

    /**
     * The port that the server is listening on. Defaults to 4040
     */
    var PORT : String = "4040"

    /**
     * The IP address for the server. An empty string indicates the IP address
     * for the machine the server is running on
     */
    var ADDRESS : String = ""

    /**
     *Server version string. Supporting v1.0 of the protocol
     */
    var VERSION : String = "XBMSP-1.0 1.0 Pomelo Server\n"

    /**
     * Server comment string
     */
    var COMMENT : String = ""
}
