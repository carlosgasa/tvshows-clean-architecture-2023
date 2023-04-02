package com.gscarlos.tvshowscarlosg.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.gscarlos.tvshowscarlosg.R
import com.gscarlos.tvshowscarlosg.ui.compose.theme.TvShowsTheme
import com.gscarlos.tvshowscarlosg.ui.detail.DetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(
            ViewCompositionStrategy.DisposeOnLifecycleDestroyed(
                viewLifecycleOwner
            )
        )
        setContent {
            TvShowsTheme {
                FavoritesScreen(
                    onClickItem = {
                        findNavController().navigate(
                            R.id.action_favoritesFragment_to_detailFragment,
                            DetailFragment.getBundle(it)
                        )
                    }, onBack = {
                        findNavController().navigateUp()
                    })
            }
        }
    }
}