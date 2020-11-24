package com.intija.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.intija.data.models.user.GetUserResponse
import io.reactivex.Single

@Dao
interface DatabaseDAO {

    /**
     * Only Github user profile is being persisted because
     * it's the only kind of data in this project that actually makes
     * sense to be persisted if it were to be an actual real project
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGithubUser(user: GetUserResponse)

    @Query("SELECT * FROM github_user_table WHERE login=:githubUserName")
    fun getGithubUser(githubUserName: String): Single<GetUserResponse>
}