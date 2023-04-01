package com.gscarlos.tvshowscarlosg.data

import com.gscarlos.tvshowscarlosg.data.remote.responses.Show
import com.gscarlos.tvshowscarlosg.data.remote.responses.TVShowDto
import com.gscarlos.tvshowscarlosg.data.remote.responses.TVShowSearchedDto
import com.gscarlos.tvshowscarlosg.domain.model.TVShow
import com.gscarlos.tvshowscarlosg.domain.model.TVShowDetail

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

fun Show.toTvShowDetail() = TVShowDetail(
    id,
    name,
    network?.name ?: "",
    "${schedule.time} | ${schedule.days.toCustomString()}",
    image?.medium ?: "",
    links?.self?.href ?: "",
    rating.average.toString(),
    summary?: "",
    genres.toCustomString(),

)

private fun List<String>.toCustomString() = if (isEmpty()) ""
else {
    var result = ""
    forEachIndexed { index, it ->
        result += if(index == size - 1) it
        else "$it, "
    }
    result
}
