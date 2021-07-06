package com.osung.beer.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.osung.beer.domain.entity.Beer
import com.osung.beer.domain.repository.BeerRepository

/**
 * 맥주 전체 검색 UseCase
 *
 * @property repository
 */
class GetBeers(
    private val repository: BeerRepository
) {
    operator fun invoke() : LiveData<PagingData<Beer>> {
        return repository.getBeerPage()
    }
}