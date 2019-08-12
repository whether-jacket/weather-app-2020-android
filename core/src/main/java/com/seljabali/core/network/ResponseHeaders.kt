package com.seljabali.core.network

enum class ResponseHeaders(val id: String) {
    ACCESS_CONTROL_ALLOW_ORIGIN("Access-Control-Allow-Origin"),
    EXPIRES("Expires"),
    SET_COOKIE("Set-Cookie"),
    STRICT_TRANSPORT_SECURITY("Strict-Transport-Security"),
}