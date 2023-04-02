package com.gscarlos.tvshowscarlosg.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.gscarlos.tvshowscarlosg.commons.loadImage
import com.gscarlos.tvshowscarlosg.commons.toHtml
import com.gscarlos.tvshowscarlosg.databinding.FragmentDetailBinding
import com.gscarlos.tvshowscarlosg.ui.compose.composables.PersonItem
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

    private fun initElements() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initUiState() = with(binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.detailState.flowWithLifecycle(viewLifecycleOwner.lifecycle).collect {
                when(it) {
                    DetailUiState.Start -> {}
                    is DetailUiState.SuccessData -> {
                        ivPortada.loadImage(it.tvShow.imageMedium)
                        tvTitle.text = it.tvShow.name
                        tvNetwork.text = it.tvShow.network
                        tvRating.text = "Rating: ${it.tvShow.rating}"
                        tvSummary.text = "<b>Sinopsis:</b> <br /> ${it.tvShow.summary}".toHtml()
                        tvGenres.text = "<b>Géneros:</b> ${it.tvShow.genres}".toHtml()
                        tvTime.text = "<b>Horario:</b> ${it.tvShow.dates}".toHtml()
                        cvCast.setContent { 
                            if (it.tvShow.cast.isNotEmpty()) {
                                Column() {
                                    Text(text = "Talentos:")
                                    LazyRow(contentPadding = PaddingValues(vertical = 4.dp)) {
                                        items(it.tvShow.cast) { person ->
                                            PersonItem(person = person)
                                        }
                                    }
                                }
                            }
                        }
                    }
                    is DetailUiState.Error -> {

                    }
                    DetailUiState.Loading -> {

                    }
                }

            }
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