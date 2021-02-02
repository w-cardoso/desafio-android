package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.MainRepository
import com.picpay.desafio.android.network.PicPayService

class MainRepositoryImpl(private val apiService: PicPayService) : MainRepository {

    override suspend fun getUsers(): List<User> {
        return apiService.getUsers()
    }

}