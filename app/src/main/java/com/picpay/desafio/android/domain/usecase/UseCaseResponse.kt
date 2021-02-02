package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.domain.model.ApiError


interface UseCaseResponse<T> {

    fun onSuccess(result: T)

    fun onError(apiError: ApiError?)
}

