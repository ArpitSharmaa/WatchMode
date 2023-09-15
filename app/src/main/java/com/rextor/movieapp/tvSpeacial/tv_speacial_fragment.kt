package com.rextor.movieapp.tvSpeacial

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
import com.rextor.movieapp.TVshows.tvShowViewModel
import com.rextor.movieapp.Utils.CurrentPosition
import com.rextor.movieapp.Utils.Positions
import com.rextor.movieapp.Utils.coroutineexceptionhandler
import com.rextor.movieapp.databinding.FragmentTvShowsFragmentBinding
import com.rextor.movieapp.databinding.FragmentTvSpeacialFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class tv_speacial_fragment : Fragment() {

    private var _binding : FragmentTvSpeacialFragmentBinding?= null
    val binding
        get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CurrentPosition._positions.value= Positions.TV_SPEACIAL
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentTvSpeacialFragmentBinding.inflate(
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
            .get(tvspecialViewModel::class.java)
        val adaptor = Adaptor(){
            val bundle = Bundle()
            bundle.putString(
                "tille",
                it
            )
            bundle.putString(
                "type",
                Type.tv_speacial.type
            )
            findNavController().navigate(R.id.action_tv_speacial_fragment_to_details_fragment,bundle)
        }
        binding?.listOfTvspeacial?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adaptor
        }
        val coroutineexceptionhandler = coroutineexceptionhandler(requireContext())
        lifecycleScope.launch (coroutineexceptionhandler.coroutineExceptionHandler){
            viewModel.listOftvspeacials.collectLatest {
                if (it.listofTitles.isNotEmpty()){
                    binding?.progressBartvspeacial?.visibility  = View.INVISIBLE
                    adaptor.submitList(it.listofTitles)
                }

            }
        }
    }

    override fun onResume() {
        super.onResume()
        CurrentPosition._positions.value= Positions.TV_SPEACIAL
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null

    }
}