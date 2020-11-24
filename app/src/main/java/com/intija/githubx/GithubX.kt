package com.intija.githubx

import com.intija.githubx.injection.AppComponent
import com.intija.githubx.injection.DaggerAppComponent
import dagger.android.DaggerApplication


class GithubX: DaggerApplication() {
    override fun applicationInjector(): AppComponent {
        return DaggerAppComponent.builder().application(this).build()
    }
}