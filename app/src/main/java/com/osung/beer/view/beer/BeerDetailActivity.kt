package com.osung.beer.view.beer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.osung.beer.R
import com.osung.beer.databinding.ActivityBeerDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class BeerDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBeerDetailBinding
    private val detailViewModel: BeerDetailViewModel by viewModel {
        intent.getIntExtra(INTENT_BEER_ID, -1).run {
            parametersOf(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_beer_detail)

        init()
    }

    private fun init() {
        with(binding) {
            viewModel = this@BeerDetailActivity.detailViewModel
            lifecycleOwner = this@BeerDetailActivity
        }

        detailViewModel.error.observe(this) {
            if(it) {
                Toast.makeText(this, R.string.error_beer_detail, Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    companion object {
        const val INTENT_BEER_ID = "intent_beer_id"

        fun startActivity(context: Context, beerId: Int) {
            Intent(context, BeerDetailActivity::class.java).run {
                putExtra(INTENT_BEER_ID, beerId)

                context.startActivity(this)
            }
        }
    }
}