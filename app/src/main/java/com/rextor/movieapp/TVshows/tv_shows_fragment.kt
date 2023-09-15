package com.rextor.movieapp.TVshows

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rextor.movieapp.CommonAdaptor.Adaptor
import com.rextor.movieapp.Model.Type
import com.rextor.movieapp.R
import com.rextor.movieapp.Utils.CurrentPosition
import com.rextor.movieapp.Utils.Positions
import com.rextor.movieapp.Utils.coroutineexceptionhandler
import com.rextor.movieapp.databinding.FragmentMoviesListFragmentBinding
import com.rextor.movieapp.databinding.FragmentTvShowsFragmentBinding
import com.rextor.movieapp.movies.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch



@AndroidEntryPoint
class tv_shows_fragment : Fragment() {
    private var _binding : FragmentTvShowsFragmentBinding?= null
    val binding
        get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CurrentPosition._positions.value = Positions.TV_SHOWS
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentTvShowsFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        val view = binding?.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)
            .get(tvShowViewModel::class.java)
        val adaptor = Adaptor(){
            val bundle = Bundle()
            bundle.putString(
                "tille",
                it
            )
            bundle.putString(
                "type",
                Type.tv_series.type
            )
            findNavController().navigate(R.id.action_tv_shows_fragment_to_details_fragment,bundle)
        }
        val coroutineexceptionhandler = coroutineexceptionhandler(requireContext())
        binding?.listOfTvseries?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adaptor
        }
        lifecycleScope.launch (coroutineexceptionhandler.coroutineExceptionHandler){
            viewModel.listOftvshows.collectLatest {
                if (it.listofTitles.isNotEmpty()){
                    binding?.progressBartvseries?.visibility  = View.INVISIBLE
                    adaptor.submitList(it.listofTitles)
                }

            }
        }

    }

    override fun onResume() {
        super.onResume()
        CurrentPosition._positions.value = Positions.TV_SHOWS

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding= null
    }

}