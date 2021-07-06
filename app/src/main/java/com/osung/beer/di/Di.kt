package com.osung.beer.di

import com.osung.beer.data.datasource.BeerDataSource
import com.osung.beer.data.remote.Api
import com.osung.beer.data.remote.RemoteBeerDataSourceImpl
import com.osung.beer.data.repository.BeerRepositoryImpl
import com.osung.beer.domain.repository.BeerRepository
import com.osung.beer.domain.usecase.GetBeers
import com.osung.beer.domain.usecase.GetSearchBeerId
import com.osung.beer.domain.usecase.GetSearchBeerName
import com.osung.beer.view.beer.BeerDetailViewModel
import com.osung.beer.view.main.MainViewModel
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
    viewModel { (param: Int) -> BeerDetailViewModel(param, get()) }
}

val useCaseModule = module {
    single { GetBeers(get()) }
    single { GetSearchBeerName(get()) }
    single { GetSearchBeerId(get()) }
}

val dataModule = module {
    single<BeerRepository> { BeerRepositoryImpl(get()) }
    single<BeerDataSource> { RemoteBeerDataSourceImpl(get()) }
}

 val networkModule = module {
    single {
        Interceptor { chain ->
            chain.proceed(chain.request().newBuilder().apply {
                header("accept", "application/vnd.github.v3+json")
            }.build())
        }
    }

    single {
        OkHttpClient.Builder().apply {
            addInterceptor(get())
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }.build()
    }

    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Api.baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(get())
            .build()
            .create(Api::class.java)
    }
}