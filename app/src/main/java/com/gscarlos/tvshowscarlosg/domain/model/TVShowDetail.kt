package com.gscarlos.tvshowscarlosg.domain.model

data class TVShowDetail(
    val id: Long,
    val name: String,
    val network: String,
    val dates: String,
    val imageMedium: String,
    val link: String,
    val rating: String,
    val summary: String,
    val genres: String,
    val cast: List<Person>
)
