package com.osung.beer.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.osung.beer.domain.entity.Beer
import com.osung.beer.domain.repository.BeerRepository

/**
 * 검색어를 포함한 맥주 리스트 검색 UseCase
 *
 * @property repository
 */
class GetSearchBeerName(
    private val repository: BeerRepository
) {
    operator fun invoke(query: String) : LiveData<PagingData<Beer>> {
        return repository.getSearchBeerNamePage(query)
    }
}