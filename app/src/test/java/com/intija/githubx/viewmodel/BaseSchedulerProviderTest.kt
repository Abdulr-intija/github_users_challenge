package com.intija.githubx.viewmodel

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class BaseSchedulerProviderTest : BaseSchedulerProvider {

    override fun io(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun ui(): Scheduler {
        return Schedulers.trampoline()
    }
}