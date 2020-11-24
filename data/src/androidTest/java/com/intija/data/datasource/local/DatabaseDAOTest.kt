package com.intija.data.datasource.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.intija.data.models.user.GetUserResponse
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class DatabaseDAOTest {

    private lateinit var database: GithubUsersDatabase
    private lateinit var dao: DatabaseDAO

    /**
     * Setup database test instances*/
    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            GithubUsersDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.databaseDAO
    }

    @After
    fun destroy(){
        database.close()
    }

    @Test
    fun userInsertTests() = runBlockingTest {
        assert(testUserInsert("random_github_user_1"))
        assert(testUserInsert("random_github_user_2"))
        assert(testUserInsert("random_github_user_3"))
    }

    private fun testUserInsert(username: String): Boolean {
        val githubUser = initGithubuserData(username)

        //insert user
        dao.insertGithubUser(githubUser)

        //fetch user
        val fetchedUser = dao.getGithubUser(username).blockingGet()

        return fetchedUser == githubUser
    }

    private fun initGithubuserData(username: String): GetUserResponse{
        return GetUserResponse(
            0,
            "https://testurl.com",
        "Test bio",
            "Tst blog",
            "Test company",
            "2020-10-10",
            "test@email.com",
            "https://test.url",
            0,
            "https://followers.url",
            20,
            "https://following.url",
            "https://test.url",
            "3312",
            true,
            "https://test.url",
            21312,
            "Test location",
            username,
            "Test name",
            "3243",
            "https://test.url",
            324,
            23,
            "https://test.url",
            "https://test.url",
            false,
            "https://test.url",
            "https://test.url",
            "testTwitter",
            "test",
            "2020-12-15",
            "https://test.url",
        )
    }
}