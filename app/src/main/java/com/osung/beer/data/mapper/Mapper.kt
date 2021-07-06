package com.osung.beer.data.mapper

import com.osung.beer.data.remote.model.ResponseBeerItem
import com.osung.beer.domain.entity.Beer

fun ResponseBeerItem.mapper(): Beer {
    return Beer(id, name, tagline, description, imageUrl, abv, ibu)
}