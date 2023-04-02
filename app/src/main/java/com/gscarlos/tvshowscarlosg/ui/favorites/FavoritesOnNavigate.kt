package com.gscarlos.tvshowscarlosg.ui.favorites

sealed class FavoritesOnNavigate {
    object OnBack : FavoritesOnNavigate()
    class OnShowDetail(val id: String) : FavoritesOnNavigate()
}
