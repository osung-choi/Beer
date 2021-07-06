package com.osung.beer.domain.usecase

import com.osung.beer.domain.entity.Beer
import com.osung.beer.domain.repository.BeerRepository
import io.reactivex.rxjava3.core.Single

/**
 * ID로 맥주 검색 UseCase
 *
 * @property repository
 */
class GetSearchBeerId(
    private val repository: BeerRepository
) {
    operator fun invoke(beerId: Int): Single<Beer> {
        return repository.getSearchBeerId(beerId)
    }
}