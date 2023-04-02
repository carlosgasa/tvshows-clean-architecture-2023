package com.gscarlos.tvshowscarlosg.data

import com.gscarlos.tvshowscarlosg.commons.Constants.NO_IMAGE_YET
import com.gscarlos.tvshowscarlosg.data.local.model.TVShowEntity
import com.gscarlos.tvshowscarlosg.data.remote.responses.PersonDto
import com.gscarlos.tvshowscarlosg.data.remote.responses.Show
import com.gscarlos.tvshowscarlosg.data.remote.responses.TVShowDto
import com.gscarlos.tvshowscarlosg.data.remote.responses.TVShowSearchedDto
import com.gscarlos.tvshowscarlosg.domain.model.TVShow
import com.gscarlos.tvshowscarlosg.domain.model.Person
import com.gscarlos.tvshowscarlosg.domain.model.TVShowDetail

fun TVShowDto.toTvShow(favorite: Boolean) = TVShow(
    id,
    name,
    show.network?.name ?: "",
    "$airdate | $airtime",
    show.image?.medium ?: NO_IMAGE_YET,
    favorite
)

fun TVShowSearchedDto.toTvShowSearched(favorite: Boolean) = TVShow(
    show.id,
    show.name,
    show.network?.name ?: "",
    "${show.schedule.time} | ${show.schedule.days.toCustomString()}",
    show.image?.medium ?: NO_IMAGE_YET,
    favorite
)

fun Show.toTvShowDetail(favorite: Boolean) = TVShowDetail(
    id,
    name,
    network?.name ?: "",
    "${schedule.time} | ${schedule.days.toCustomString()}",
    image?.medium ?: NO_IMAGE_YET,
    links?.self?.href ?: "",
    rating.average.toString(),
    summary ?: "",
    genres.toCustomString(),
    emptyList(),
    favorite
)

fun Show.toTvShowDetail(favorite: Boolean, cast: List<Person>) = TVShowDetail(
    id,
    name,
    network?.name ?: "",
    "${schedule.time} | ${schedule.days.toCustomString()}",
    image?.medium ?: NO_IMAGE_YET,
    officialSite ?: "",
    rating.average.toString(),
    summary ?: "",
    genres.toCustomString(),
    cast,
    favorite
)

fun PersonDto.toPerson() = Person(
    id,
    name,
    image?.medium ?: NO_IMAGE_YET,
)

fun TVShow.toEntity() = TVShowEntity(
    id,
    name,
    network,
    dates,
    imageMedium,
)

private fun List<String>.toCustomString() = if (isEmpty()) ""
else {
    var result = ""
    forEachIndexed { index, it ->
        result += if (index == size - 1) it
        else "$it, "
    }
    result
}
