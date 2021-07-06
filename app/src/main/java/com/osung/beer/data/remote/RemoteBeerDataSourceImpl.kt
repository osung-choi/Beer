package com.osung.beer.data.remote

import com.osung.beer.data.datasource.BeerDataSource
import com.osung.beer.data.remote.model.ResponseBeer
import com.osung.beer.data.remote.model.ResponseBeerItem
import io.reactivex.rxjava3.core.Single

class RemoteBeerDataSourceImpl(
    private val api: Api
): BeerDataSource {
    override fun requestBeerPage(page: Int, pageSize: Int): Single<ResponseBeer> {
        return api.requestBeerPage(page, pageSize)
    }

    override fun requestSearchBeerNamePage(query: String, page: Int, pageSize: Int): Single<ResponseBeer> {
        val apiBeerName = query.replace("[ ]".toRegex(),"_")
        return api.requestSearchBeerNamePage(apiBeerName, page, pageSize)
    }

    override fun requestSearchBeerId(beerId: Int): Single<List<ResponseBeerItem>> {
        return api.requestSearchBeerId(beerId)
    }
}