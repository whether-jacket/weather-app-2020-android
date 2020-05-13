package com.seljabali.network

// https://stackoverflow.com/questions/23714383/what-are-all-the-possible-values-for-http-content-type-header
enum class ContentTypes(val id: String) {
    // Application types
    EDI("application/EDI-X12"),
    EDIFACT("application/EDIFACT"),
    JAVASCRIPT("application/javascript"),
    OCTET_STREAM("application/octet-stream"),
    OGG("application/ogg"),
    PDF("application/pdf"),
    XHTML("application/xhtml+xml"),
    FLASH("application/x-shockwave-flash"),
    JSON("application/json"),
    LD_JSON("application/ld+json"),
    XML("application/xml"),
    ZIP("application/zip"),
    X_URL_ENCODED("application/x-www-form-urlencoded"),

    // Audio
    MGPEG("audio/mpeg"),
    WMA("audio/x-ms-wma"),
    REAL_AUDIO("audio/vnd.rn-realaudio"),
    WAV("audio/x-wav"),

    // Image
    GIF("image/gif"),
    JPEG("image/jpeg"),
    PNG("image/png"),
    TIFF("image/tiff"),
    VND_MSFT_ICON("image/vnd.microsoft.icon"),
    X_ICON("image/x-icon"),
    VND("image/vnd.djvu"),
    SVG("image/svg+xml"),

    // Multipart
    MULTI_MIXED("multipart/mixed"),
    MULTI_ALT("multipart/alternative"),
    MULTI_RELATED("multipart/related"), // (using by MHTML (HTML mail)
    MULTI_FORM_DATA("multipart/form-data"),

    // Text
    CSS("text/css"),
    CSV("text/csv"),
    HTML("text/html"),
    TEXT_JAVASCRIPT("text/javascript"), // obsolete
    PLAIN("text/plain"),
    TEXT_XML("text/xml"),

    // Video
    MPEG("video/mpeg"),
    MP4("video/mp4"),
    QUICKTIME("video/quicktime"),
    X_MS_WMV("video/x-ms-wmv"),
    X_MS_VIDEO("video/x-msvideo"),
    X_FLV("video/x-flv"),
    WEBM("video/webm"),

    // VND
    VND_TEXT("application/vnd.oasis.opendocument.text"),
    VND_SPREADSHEET("application/vnd.oasis.opendocument.spreadsheet"),
    VND_PRESENTATION("application/vnd.oasis.opendocument.presentation"),
    VND_GRAPHICS("application/vnd.oasis.opendocument.graphics"),
    VND_EXCEL("application/vnd.ms-excel"),
    VND_OFFICE_SPREADSHEET("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    VND_POWERPOINT("application/vnd.ms-powerpoint"),
    VND_OFFICE_PRESENTATION("application/vnd.openxmlformats-officedocument.presentationml.presentation"),
    VND_MSWORD("application/msword"),
    VND_OPEN_OFFICE_WORD("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
    VND_MOZILLA_XUL("application/vnd.mozilla.xul+xml");
}
