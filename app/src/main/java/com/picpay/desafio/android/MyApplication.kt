package com.picpay.desafio.android

import android.app.Application
import androidx.multidex.MultiDex
import com.picpay.desafio.android.di.AppModule
import com.picpay.desafio.android.di.NetworkModule
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(AppModule, NetworkModule))
        }

    }
}