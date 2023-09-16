package com.rextor.movieapp.favourites

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
import com.rextor.movieapp.databinding.FragmentFavoriteFragmentBinding
import com.rextor.movieapp.databinding.FragmentMoviesListFragmentBinding
import com.rextor.movieapp.movies.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class favorite_fragment : Fragment() {

    private var _binding : FragmentFavoriteFragmentBinding?= null
    val binding
        get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CurrentPosition._positions.value = Positions.FAVOURITES
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentFavoriteFragmentBinding.inflate(
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
            .get(favviewmodel::class.java)
        val adaptor = favAdaptor(){
            val bundle = Bundle()
            bundle.putString(
                "tille",
                it.get(0)
            )
            bundle.putString(
                "type",
                it.get(1)
            )
            findNavController().navigate(R.id.action_favorite_fragment_to_details_fragment,bundle)
        }
        binding?.favList?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adaptor
        }
        lifecycleScope.launch (coroutineexceptionhandler.coroutineExceptionHandler){
            viewModel.listoffav.collectLatest {
                if (it.isNotEmpty()){
                    binding?.progressBarfav?.visibility  = View.INVISIBLE
                    adaptor.submitList(it)
                }

            }
        }

    }
    override fun onResume() {
        super.onResume()
        CurrentPosition._positions.value = Positions.FAVOURITES
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}