package com.rextor.movieapp.movies

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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class movies_list_fragment : Fragment() {

    private var _binding : FragmentMoviesListFragmentBinding?= null
    val binding
        get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CurrentPosition._positions.value = Positions.MOVIES
        arguments?.let {

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       _binding= FragmentMoviesListFragmentBinding.inflate(
           inflater,
           container,
           false
       )
        val view = binding?.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val coroutineexceptionhandler = coroutineexceptionhandler(requireContext())
        val viewModel = ViewModelProvider(this)
            .get(MoviesViewModel::class.java)
        val adaptor = Adaptor(){
            val bundle = Bundle()
            bundle.putString(
                "tille",
                it
            )
            bundle.putString(
                "type",
                Type.movies.type
            )
            findNavController().navigate(R.id.action_movies_list_fragment_to_details_fragment,bundle)
        }
        binding?.movieList?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adaptor
        }
        lifecycleScope.launch (coroutineexceptionhandler.coroutineExceptionHandler){
            viewModel.listOfMovies.collectLatest {
                if (it.listofTitles.isNotEmpty()){
                    binding?.progressBar?.visibility  = View.INVISIBLE
                    adaptor.submitList(it.listofTitles)
                }

            }
        }


    }

    override fun onResume() {
        super.onResume()
        CurrentPosition._positions.value = Positions.MOVIES
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }


}