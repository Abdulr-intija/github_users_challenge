package com.intija.githubx.viewmodel

import io.reactivex.Scheduler

interface BaseSchedulerProvider {

    fun io(): Scheduler

    fun ui(): Scheduler

}