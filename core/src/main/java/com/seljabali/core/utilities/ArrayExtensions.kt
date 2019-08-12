package com.seljabali.core.utilities

fun <S> Array<S>.asArrayList(): ArrayList<S> = ArrayList(this.asList())