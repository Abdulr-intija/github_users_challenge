package com.intija.data.injection

import android.app.Application
import com.intija.data.datasource.local.DatabaseDAO
import com.intija.data.datasource.local.GithubUsersDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDao(database: GithubUsersDatabase): DatabaseDAO {
        return database.databaseDAO
    }

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Application): GithubUsersDatabase {
        return GithubUsersDatabase.getInstance(context)
    }
}