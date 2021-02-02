package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.MainRepository

class MainUseCase(private val repository: MainRepository) : UseCase<List<User>, Any?>() {

    override suspend fun run(params: Any?): List<User> {
        return  repository.getUsers()
    }
}