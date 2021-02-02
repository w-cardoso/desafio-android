package com.picpay.desafio.android.di

import com.google.gson.Gson
import com.picpay.desafio.android.domain.repository.MainRepository
import com.picpay.desafio.android.data.repository.MainRepositoryImpl
import com.picpay.desafio.android.domain.usecase.MainUseCase
import com.picpay.desafio.android.network.PicPayService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val url = "http://careers.picpay.com/tests/mobdev/"

val NetworkModule = module {
    single { createService(get()) }
    single { createRetrofit(get(), url) }
    single { createOkHttpClient() }
}

fun createOkHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()
    return builder.build()
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(Gson().newBuilder().create()))
        .build()
}

fun createService(retrofit: Retrofit): PicPayService {
    return retrofit.create(PicPayService::class.java)
}

fun createMainRepository(apiService: PicPayService): MainRepository {
    return MainRepositoryImpl(apiService)
}

fun createGetMainUseCase(mainRepository: MainRepository): MainUseCase {
    return MainUseCase(mainRepository)
}
