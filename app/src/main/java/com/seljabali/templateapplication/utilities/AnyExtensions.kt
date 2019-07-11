package com.seljabali.templateapplication.utilities

fun <S> S.asArrayList(): ArrayList<S> = ArrayList<S>().apply { add(this@asArrayList) }