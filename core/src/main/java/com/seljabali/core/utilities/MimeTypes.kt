package com.seljabali.core.utilities

// IANA list: https://www.iana.org/assignments/media-types/media-types.xhtml
enum class MimeTypes(val id: String) {
    ANY("*/*"),
    APP_JAVASCRIPT("application/javascript"),
    APP_JSON("application/json"),
    APP_MSWORD("application/msword"),
    APP_PDF("application/pdf"),
    APP_SQL("application/sql"),
    APP_XML("application/xml"),
    APP_ZIP("application/zip"),
    AUDIO_ANY("audio/*"),
    AUDIO_MPEG("audio/mpeg"),
    AUDIO_OGG("audio/ogg"),
    IMAGE_ANY("image/*"),
    IMAGE_GIF("image/gif"),
    IMAGE_WEBP("image/webp"),
    IMAGE_JPEG("image/jpeg"),
    IMAGE_PNG("image/png"),
    MESSAGE_EMAIL("message/rfc822"),
    MULTIPART_FORM_DATA("multipart/form-data"),
    TEXT_ANY("text/*"),
    TEXT_CSS("text/css"),
    TEXT_CSV("text/csv"),
    TEXT_HTML("text/html"),
    TEXT_PHP("text/php"),
    TEXT_PLAIN("text/plain"),
    TEXT_XML("text/xml"),
    VIDEO_ANY("video/*"),
}