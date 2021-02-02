package com.picpay.desafio.android.di

import com.picpay.desafio.android.presentation.MainViewModel
import com.picpay.desafio.android.presentation.adapter.UserListAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {

    viewModel { MainViewModel(get()) }

    factory<UserListAdapter> { UserListAdapter() }

    single { createGetMainUseCase(get()) }

    single { createMainRepository(get()) }
}