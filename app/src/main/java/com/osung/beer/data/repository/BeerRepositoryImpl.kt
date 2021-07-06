package com.osung.beer.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.paging.*
import com.osung.beer.PAGE_SIZE
import com.osung.beer.data.datasource.BeerDataSource
import com.osung.beer.data.remote.paging.BeerPagingDataSource
import com.osung.beer.data.remote.paging.SearchBeerNamePagingDataSource
import com.osung.beer.domain.repository.BeerRepository
import com.osung.beer.domain.entity.Beer
import com.osung.beer.data.mapper.mapper
import io.reactivex.rxjava3.core.Single

class BeerRepositoryImpl(
    private val remote: BeerDataSource
): BeerRepository {
    override fun getBeerPage(): LiveData<PagingData<Beer>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { BeerPagingDataSource(remote) }
        ).liveData
            .map { data -> data.map { it.mapper() } }
    }

    override fun getSearchBeerNamePage(query: String): LiveData<PagingData<Beer>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SearchBeerNamePagingDataSource(query, remote) }
        ).liveData
            .map { data -> data.map { it.mapper() } }
    }

    override fun getSearchBeerId(beerId: Int): Single<Beer> {
        return remote.requestSearchBeerId(beerId)
            .map { it[0].mapper() } //response 가 리스트 형태로 제공되고 0번 아이템에 id와 일치하는 맥주 정보가 담겨있다.
    }
}