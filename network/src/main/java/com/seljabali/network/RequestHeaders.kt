package com.seljabali.network

// Taken from: https://en.wikipedia.org/wiki/List_of_HTTP_header_fields
enum class RequestHeaders(val id: String) {
    AUTH("Authorization"),
    ACCEPT("Accept"),
    ACCESS_CONTROL_REQUEST("Access-Control-Request-Method"),
    CACHE_CONTROL("Cache-Control"),
    CONNECTION("Connection"),
    CONTENT_LENGTH("Content-Length"),
    CONTENT_TYPE("Content-Type"),
    COOKIE("Cookie"),
    DATE("Date"),
    HTTP2_SETTINGS("HTTP2-Settings"),
    ORIGIN("Origin");
}