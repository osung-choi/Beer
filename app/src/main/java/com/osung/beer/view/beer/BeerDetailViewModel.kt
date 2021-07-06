package com.osung.beer.view.beer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.osung.beer.domain.entity.Beer
import com.osung.beer.domain.usecase.GetSearchBeerId
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class BeerDetailViewModel(
    beerId: Int,
    getSearchBeerId: GetSearchBeerId
): ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    /**
     * 에러 관찰자
     */
    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    /**
     * 맥주 아이템 관찰자
     */
    private val _beerItem = MutableLiveData<Beer>()
    val beerItem: LiveData<Beer> = _beerItem

    /**
     * 파라미터로 전달받은 맥주 id 값으로 맥주 검색
     */
    init {
        getSearchBeerId(beerId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ _beerItem.value = it }, { _error.value = true })
            .run { compositeDisposable.add(this) }
    }

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.clear()
    }
}