package com.osung.beer.domain.entity

data class Beer(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val imageUrl: String?,
    val abv: Double, //알코올 수치
    val ibu: Double //쓴맛 수치
)
