package com.osung.beer.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.osung.beer.domain.entity.Beer
import io.reactivex.rxjava3.core.Single

interface BeerRepository {
    /**
     * 맥주 전체 리스트 페이지 얻어온다.
     *
     * @return 맥주 페이지 관찰자
     */
    fun getBeerPage(): LiveData<PagingData<Beer>>

    /**
     * 검색어를 포함한 맥주 리스트 페이지 얻어온다.
     *
     * @param query 검색어
     * @return 맥주 페이지 관찰자
     */
    fun getSearchBeerNamePage(query: String): LiveData<PagingData<Beer>>

    /**
     * ID로 검색한 맥주 얻어온다.
     *
     * @param beerId 맥주 ID
     * @return
     */
    fun getSearchBeerId(beerId: Int): Single<Beer>
}