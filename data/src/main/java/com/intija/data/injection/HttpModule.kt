package com.intija.data.injection

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class HttpModule {
    @Singleton
    @Provides
    fun provideApiKeyInterceptor() = APITokenInterceptor()

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Singleton
    @Provides
    fun getAuthorizedOkHttpClient(
        interceptor: APITokenInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS) // write timeout
            .readTimeout(30, TimeUnit.SECONDS) // read timeout
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }
}