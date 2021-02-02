package com.picpay.desafio.android.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.model.ApiError
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.usecase.MainUseCase
import com.picpay.desafio.android.domain.usecase.UseCaseResponse
import kotlinx.coroutines.cancel

class MainViewModel(private val useCase: MainUseCase) : ViewModel() {
    private val state: MutableLiveData<MainDataStates> = MutableLiveData()
    val viewStates: LiveData<MainDataStates> = state

    fun interpret(interactor: MainInteractor) = when (interactor) {
        is MainInteractor.GetListUsers -> getUsers()
    }

    private fun getUsers() {
        useCase.invoke(viewModelScope, null, object : UseCaseResponse<List<User>> {
            override fun onSuccess(result: List<User>) {
                state.postValue(MainDataStates.CallSucess(result))
            }

            override fun onError(apiError: ApiError?) {
                state.postValue(MainDataStates.CallError)
            }
        })
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}