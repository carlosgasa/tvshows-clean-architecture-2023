package com.gscarlos.tvshowscarlosg.data.remote.responses

data class ItemDto(val person: PersonDto)

data class PersonDto (
    val id: Long,
    val name: String,
    val image: Image? = null,
)
