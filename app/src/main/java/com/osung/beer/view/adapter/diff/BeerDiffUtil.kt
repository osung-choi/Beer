package com.osung.beer.view.adapter.diff

import androidx.recyclerview.widget.DiffUtil
import com.osung.beer.domain.entity.Beer

class BeerDiffUtil: DiffUtil.ItemCallback<Beer>() {
    override fun areItemsTheSame(oldItem: Beer, newItem: Beer) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Beer, newItem: Beer) = oldItem == newItem
}