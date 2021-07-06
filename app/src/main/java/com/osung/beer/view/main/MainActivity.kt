package com.osung.beer.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import com.osung.beer.R
import com.osung.beer.databinding.ActivityMainBinding
import com.osung.beer.view.adapter.BeerAdapter
import com.osung.beer.view.adapter.listener.BeerItemClickListener
import com.osung.beer.view.beer.BeerDetailActivity
import com.osung.beer.view.utils.KeyboardUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), BeerItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    private val adapter: BeerAdapter by lazy { BeerAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        init()
    }

    private fun init() {
        with(binding) {
            viewModel = this@MainActivity.viewModel
            lifecycleOwner = this@MainActivity

            beerList.adapter = adapter
        }

        viewModel.beerList.observe(this) {
            adapter.submitData(lifecycle, it)

            KeyboardUtil.hideKeyboard(this, binding.inputSearchQuery)
            binding.beerList.scrollToPosition(0)
        }

        /**
         * 초기 로딩 시 리스트의 아이템 카운트가 0일 경우 검색 실패 토스트 표출.
         */
        adapter.addLoadStateListener { loadState ->
            if (loadState.source.refresh is LoadState.NotLoading
                && loadState.append.endOfPaginationReached
                && adapter.itemCount < 1) {

                Toast.makeText(this, getString(R.string.search_result_empty), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onItemClickListener(beerId: Int) {
        BeerDetailActivity.startActivity(this, beerId)
    }
}