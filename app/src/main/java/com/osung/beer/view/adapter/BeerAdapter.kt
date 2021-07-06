package com.osung.beer.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.osung.beer.databinding.ItemBeerBinding
import com.osung.beer.domain.entity.Beer
import com.osung.beer.view.adapter.diff.BeerDiffUtil
import com.osung.beer.view.adapter.listener.BeerItemClickListener

class BeerAdapter(
    private val listener: BeerItemClickListener
): PagingDataAdapter<Beer, BeerAdapter.BeerViewHolder>(BeerDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BeerViewHolder(ItemBeerBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class BeerViewHolder(
        private val binding: ItemBeerBinding,
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(beerItem: Beer) {
            binding.beerItem = beerItem

            binding.root.setOnClickListener {
                listener.onItemClickListener(beerItem.id)
            }
        }
    }
}