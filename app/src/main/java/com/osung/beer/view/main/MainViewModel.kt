package com.osung.beer.view.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.cachedIn
import com.osung.beer.domain.usecase.GetBeers
import com.osung.beer.domain.usecase.GetSearchBeerName

class MainViewModel(
    private val getBeers: GetBeers,
    private val getSearchBeerName: GetSearchBeerName
): ViewModel() {
    /**
     * 검색어 관찰자
     */
    private val searchQuery = MutableLiveData<String>().apply { value = "" }

    /**
     * 맥주 리스트 관찰자
     * 검색어 없이 검색할 경우 전체검색, 검색어 있는 경우 검색어를 포함한 맥주 검색
     */
    val beerList = Transformations.switchMap(searchQuery) { query ->
        if(query.isEmpty()) getBeers() else getSearchBeerName(query)
    }.cachedIn(this)

    /**
     * 검색 버튼 클릭 이벤트
     *
     * @param query 검색어
     */
    fun onSearchClickEvent(query: String) {
        searchQuery.value = query
    }
}