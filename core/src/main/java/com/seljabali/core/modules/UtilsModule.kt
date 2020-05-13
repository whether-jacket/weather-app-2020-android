package com.seljabali.core.modules

import android.content.res.Resources
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val utilsModule = module {

    single<RxProvider> {
        RxProvider()
    }

    single<Resources> {
        androidContext().resources
    }
}

