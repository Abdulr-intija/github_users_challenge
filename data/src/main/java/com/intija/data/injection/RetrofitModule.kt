package com.intija.data.injection

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.intija.data.BuildConfig
import com.intija.data.datasource.remote.ServiceAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [HttpModule::class])
class RetrofitModule {
    @Singleton
    @Provides
    fun getRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideAuthorizedServiceApi(retrofit: Retrofit): ServiceAPI {
        return retrofit.create(ServiceAPI::class.java)
    }
}