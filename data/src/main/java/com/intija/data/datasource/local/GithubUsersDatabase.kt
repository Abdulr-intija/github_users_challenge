package com.intija.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.intija.data.datasource.Constants
import com.intija.data.models.user.GetUserResponse

@Database(entities = [GetUserResponse::class], version = Constants.dbVersion)
abstract class GithubUsersDatabase : RoomDatabase() {

    abstract val databaseDAO: DatabaseDAO

    companion object{
        @Volatile
        private var INSTANCE : GithubUsersDatabase? = null

        /**
         * Ensuring local DB exists and is initialized
         * **/
        fun getInstance(context: Context): GithubUsersDatabase{
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        GithubUsersDatabase::class.java,
                        Constants.dbName
                    ).build()
                }

                return instance
            }
        }
    }
}