package com.osung.beer

import android.app.Application
import com.osung.beer.di.dataModule
import com.osung.beer.di.networkModule
import com.osung.beer.di.useCaseModule
import com.osung.beer.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(networkModule, dataModule, viewModelModule, useCaseModule)
        }
    }
}

const val PAGE_SIZE = 80