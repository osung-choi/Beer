package com.osung.beer.data.remote

import com.osung.beer.data.remote.model.ResponseBeer
import com.osung.beer.data.remote.model.ResponseBeerItem
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("beers")
    fun requestBeerPage(
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): Single<ResponseBeer>

    @GET("beers")
    fun requestSearchBeerNamePage(
        @Query("beer_name") beerName: String,
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): Single<ResponseBeer>

    @GET("beers/{beerId}")
    fun requestSearchBeerId(
        @Path("beerId") beerId: Int
    ): Single<List<ResponseBeerItem>>

    companion object {
        const val baseUrl = "https://api.punkapi.com/v2/"
    }
}