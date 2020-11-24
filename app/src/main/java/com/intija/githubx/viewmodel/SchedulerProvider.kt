package com.intija.githubx.viewmodel

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class SchedulerProvider : BaseSchedulerProvider {

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}