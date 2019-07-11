package com.seljabali.templateapplication.utilities

fun <S> Array<S>.asArrayList(): ArrayList<S> = ArrayList(this.asList())