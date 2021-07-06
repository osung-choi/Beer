package com.osung.beer.data.remote.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.osung.beer.data.datasource.BeerDataSource
import com.osung.beer.data.remote.model.ResponseBeerItem
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException

/**
 * 맥주 전체 리스트 페이징 처리
 *
 * @property remote
 */
class BeerPagingDataSource(
    private val remote: BeerDataSource,
): RxPagingSource<Int, ResponseBeerItem>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, ResponseBeerItem>> {
        val position = params.key?: 1

        return remote.requestBeerPage(position, params.loadSize)
            .subscribeOn(Schedulers.io())
            .map<LoadResult<Int, ResponseBeerItem>> { result ->
                LoadResult.Page(
                    data = result,
                    prevKey = if(position == 1) null else position - 1,
                    nextKey = if(result.isEmpty()) null else position + 1
                )
            }
            .onErrorReturn { e ->
                when(e) {
                    is IOException -> LoadResult.Error(e)
                    is HttpException -> LoadResult.Error(e)
                    else -> throw e
                }
            }
    }

    override fun getRefreshKey(state: PagingState<Int, ResponseBeerItem>) = 1
}