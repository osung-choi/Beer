package com.osung.beer.data.datasource

import com.osung.beer.data.remote.model.ResponseBeer
import com.osung.beer.data.remote.model.ResponseBeerItem
import io.reactivex.rxjava3.core.Single

interface BeerDataSource {
    /**
     * 맥주 전체 리스트 페이지 요청
     *
     * @param page 페이지 번호
     * @param pageSize 페이지 크기
     * @return
     */
    fun requestBeerPage(page: Int, pageSize: Int): Single<ResponseBeer>

    /**
     * 검색어를 포함한 맥주 리스트 페이징 요청
     *
     * @param query 검색어
     * @param page 페이지 번호
     * @param pageSize 페이지 크기
     * @return
     */
    fun requestSearchBeerNamePage(query: String, page: Int, pageSize: Int): Single<ResponseBeer>

    /**
     * ID로 맥주 검색 요청
     *
     * @param beerId 맥주 ID
     * @return
     */
    fun requestSearchBeerId(beerId: Int): Single<List<ResponseBeerItem>>
}