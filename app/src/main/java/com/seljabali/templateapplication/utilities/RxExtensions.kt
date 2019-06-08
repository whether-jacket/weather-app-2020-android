package com.seljabali.templateapplication.utilities

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.observeOnIo(): Observable<T> = observeOn(Schedulers.io())

fun <T> Single<T>.observeOnIo(): Single<T> = observeOn(Schedulers.io())

fun Completable.observeOnIo(): Completable = observeOn(Schedulers.io())

fun <T> Observable<T>.observeOnMain(): Observable<T> = observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.observeOnMain(): Single<T> = observeOn(AndroidSchedulers.mainThread())

fun Completable.observeOnMain(): Completable = observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.subscribeOnIo(): Observable<T> = subscribeOn(Schedulers.io())

fun <T> Single<T>.subscribeOnIo(): Single<T> = subscribeOn(Schedulers.io())

fun Completable.subscribeOnIo(): Completable = subscribeOn(Schedulers.io())

fun <T> Observable<T>.subscribeOnMain(): Observable<T> = subscribeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.subscribeOnMain(): Single<T> = subscribeOn(AndroidSchedulers.mainThread())

fun Completable.subscribeOnMain(): Completable = subscribeOn(AndroidSchedulers.mainThread())
