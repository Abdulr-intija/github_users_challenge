package com.intija.data.injection

import com.intija.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class APITokenInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val requestBuilder = original.newBuilder()
            .url(original.url)
            .addHeader("Accept", "application/vnd.github.v3+json")
            .addHeader("Authorization", "token ${BuildConfig.GITHUB_TOKEN}")

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}