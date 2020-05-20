package com.seljabali.core.utilities

import org.koin.core.KoinComponent
import org.koin.core.inject

inline fun <reified T: Any> getKoinInstance(): T {
    return object : KoinComponent {
        val value: T by inject()
    }.value
}
