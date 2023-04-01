package com.gscarlos.tvshowscarlosg.data

import com.gscarlos.tvshowscarlosg.data.remote.responses.Show
import com.gscarlos.tvshowscarlosg.data.remote.responses.TVShowDto
import com.gscarlos.tvshowscarlosg.data.remote.responses.TVShowSearchedDto
import com.gscarlos.tvshowscarlosg.domain.model.TVShow

fun TVShowDto.toTvShow() = TVShow(
    id,
    name,
    show.network?.name ?: "",
    "$airdate | $airtime",
    show.image?.medium ?: "",
)

fun TVShowSearchedDto.toTvShowSearched() = TVShow(
    show.id,
    show.name,
    show.network?.name ?: "",
    "${show.schedule.time} | ${show.schedule.days.toCustomString()}",
    show.image?.medium ?: "",
)

fun Show.toTvShow() = TVShow(
    id,
    name,
    network?.name ?: "",
    "${schedule.time} | ${schedule.days.toCustomString()}",
    image?.medium ?: "",
)

private fun List<String>.toCustomString() = if (isEmpty()) ""
else {
    var result = ""
    forEach {
        result += "$it "
    }
    result
}
