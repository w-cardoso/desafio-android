package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.domain.model.User

interface MainRepository {

    suspend fun getUsers(): List<User>
}