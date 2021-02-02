package com.picpay.desafio.android.presentation

import com.picpay.desafio.android.domain.model.User

sealed class MainDataStates {
    data class CallSucess(val listUsers: List<User>) : MainDataStates()
    object CallError : MainDataStates()
}

sealed class MainInteractor {
    object GetListUsers : MainInteractor()
}