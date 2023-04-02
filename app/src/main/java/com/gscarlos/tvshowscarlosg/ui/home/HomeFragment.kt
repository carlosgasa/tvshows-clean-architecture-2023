package com.gscarlos.tvshowscarlosg.ui.home

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.gscarlos.tvshowscarlosg.R
import com.gscarlos.tvshowscarlosg.commons.isTablet
import com.gscarlos.tvshowscarlosg.ui.compose.theme.TvShowsTheme
import com.gscarlos.tvshowscarlosg.ui.detail.DetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.apply {
            if (isTablet()) {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR
            }
            onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        activity?.finish()
                    }
                })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(
            ViewCompositionStrategy.DisposeOnLifecycleDestroyed(
                viewLifecycleOwner
            )
        )
        setContent {
            TvShowsTheme {
                HomeScreen(onNavigateFavorites = {
                    findNavController().navigate(
                        R.id.action_homeFragment_to_favoritesFragment
                    )
                }, onClickItem = {
                    findNavController().navigate(
                        R.id.action_homeFragment_to_detailFragment,
                        DetailFragment.getBundle(it)
                    )
                })
            }
        }

    }
}