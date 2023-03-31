package com.gscarlos.tvshowscarlosg.data.remote.responses

data class TVShowDto(
    val id: Long,
    val url: String,
    val name: String,
    val season: Long,
    val number: Long? = null,
    val type: String,
    val airdate: String,
    val airtime: String,
    val airstamp: String,
    val runtime: Long? = null,
    val rating: Rating,
    val image: Image? = null,
    val summary: String,
    val show: Show,
)

data class Rating (
    val average: Double? = null
)

data class Show (
    val id: Long,
    val url: String,
    val name: String,
    val type: String,
    val language: String,
    val genres: List<String>,
    val status: String,
    val runtime: Long? = null,
    val averageRuntime: Long? = null,
    val premiered: String,
    val officialSite: String? = null,
    val rating: Rating,
    val weight: Long,
    val network: Network? = null,
    val image: Image,
    val summary: String? = null,
    val updated: Long,
    val links: ShowLinks
)

data class ShowLinks (
    val self: Self,
)

data class Self (
    val href: String
)

data class Image (
    val medium: String,
    val original: String
)

data class Network (
    val id: Long,
    val name: String,
    val officialSite: String? = null
)