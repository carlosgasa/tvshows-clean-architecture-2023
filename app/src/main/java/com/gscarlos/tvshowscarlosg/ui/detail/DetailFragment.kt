package com.gscarlos.tvshowscarlosg.ui.detail

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsIntent.COLOR_SCHEME_DARK
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.BuildConfig
import com.gscarlos.tvshowscarlosg.R
import com.gscarlos.tvshowscarlosg.commons.loadImage
import com.gscarlos.tvshowscarlosg.commons.toHtml
import com.gscarlos.tvshowscarlosg.data.DataResultError
import com.gscarlos.tvshowscarlosg.databinding.FragmentDetailBinding
import com.gscarlos.tvshowscarlosg.domain.model.TVShowDetail
import com.gscarlos.tvshowscarlosg.ui.compose.composables.PersonItem
import com.gscarlos.tvshowscarlosg.ui.compose.theme.TvShowsTheme
import com.gscarlos.tvshowscarlosg.ui.home.ShowInfoState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initElements()
        initUiState()
        arguments?.getString(ARG_SHOW_ID)?.let { viewModel.loadShow(it) }
    }

    private fun initElements() = with(binding){
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.detailState.flowWithLifecycle(viewLifecycleOwner.lifecycle).collect {
                when (it) {
                    DetailUiState.Start -> {}
                    is DetailUiState.SuccessData -> {
                        fillContent(it.tvShow)
                    }
                    is DetailUiState.Error -> {
                        resolveError(it.type)
                    }
                    DetailUiState.Loading -> {
                        loading()
                    }
                }

            }
        }
    }

    private fun loading() = with(binding) {
        cvStateInfo.visibility = View.VISIBLE
        container.visibility = View.GONE

        cvStateInfo.setContent {
            TvShowsTheme() {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }

    private fun resolveError(type: DataResultError) = with(binding) {
        cvStateInfo.visibility = View.VISIBLE
        container.visibility = View.GONE

        cvStateInfo.setContent {
            TvShowsTheme() {
                ShowInfoState(type) {
                    arguments?.getString(ARG_SHOW_ID)?.let { viewModel.loadShow(it) }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun fillContent(tvShow: TVShowDetail) = with(binding) {
        cvStateInfo.visibility = View.GONE
        container.visibility = View.VISIBLE

        ivPortada.loadImage(tvShow.imageMedium)
        tvTitle.text = tvShow.name
        tvNetwork.text = tvShow.network
        tvRating.text = "Rating: ${tvShow.rating}"
        tvSummary.text = "<b>Sinopsis:</b> <br /> ${tvShow.summary}".toHtml()
        tvGenres.text = "<b>Géneros:</b> ${tvShow.genres}".toHtml()
        tvTime.text = "<b>Horario:</b> ${tvShow.dates}".toHtml()
        if(tvShow.link.isNotEmpty()) {
            btnSite.setOnClickListener {
                openTab(tvShow.link)
            }
        } else {
            btnSite.visibility = View.INVISIBLE
        }

        cvCast.setContent {
            TvShowsTheme() {
                if (tvShow.cast.isNotEmpty()) {
                    Column() {
                        Text(
                            text = "Talentos:",
                            color = MaterialTheme.colors.onBackground,
                            fontWeight = FontWeight.Bold,
                        )
                        LazyRow(contentPadding = PaddingValues(vertical = 4.dp)) {
                            items(tvShow.cast) { person ->
                                PersonItem(person = person)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun openTab(link: String) = with(binding.root.context) {
        CustomTabsIntent.Builder().apply {
            setColorScheme(COLOR_SCHEME_DARK)
            build().launchUrl(this@with, Uri.parse(link))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_SHOW_ID = "arg_show_id"

        fun getBundle(showId: String) = bundleOf(ARG_SHOW_ID to showId)
    }
}