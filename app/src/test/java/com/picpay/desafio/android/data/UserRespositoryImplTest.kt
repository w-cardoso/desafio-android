package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.repository.MainRepositoryImpl
import com.picpay.desafio.android.domain.model.User
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

class UserRespositoryImplTest {

    @MockK
    lateinit var userRespository: MainRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun getPostsData() = runBlocking {
        val users = mockk<List<User>>()
        every { runBlocking { userRespository.getUsers() } } returns (users)

        val result = userRespository.getUsers()
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$users] must be matches on each other!",
            result,
            CoreMatchers.`is`(users)
        )
    }
}