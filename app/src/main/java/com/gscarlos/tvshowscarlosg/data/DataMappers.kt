package com.gscarlos.tvshowscarlosg.data

import com.gscarlos.tvshowscarlosg.data.remote.responses.TVShowDto
import com.gscarlos.tvshowscarlosg.domain.model.TVShow

fun TVShowDto.toTvShow() = TVShow(
    id,
    name,
    show.network?.name ?: "",
    "$airdate | $airtime",
    show.image.medium,
)