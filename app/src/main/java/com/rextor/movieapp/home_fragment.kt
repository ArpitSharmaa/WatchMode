package com.rextor.movieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rextor.movieapp.Utils.CurrentPosition
import com.rextor.movieapp.Utils.Positions
import com.rextor.movieapp.databinding.FragmentHomeFragmentBinding


class home_fragment : Fragment() {


    private var _binding : FragmentHomeFragmentBinding? = null
    private val binding
        get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CurrentPosition._positions.value = Positions.HOME
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentHomeFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        val view = binding?.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.movies?.setOnClickListener {

            findNavController().navigate(R.id.action_home_fragment_to_movies_list_fragment)
        }
        binding?.tvShows?.setOnClickListener {
            findNavController().navigate(R.id.action_home_fragment_to_tv_shows_fragment)
        }
        binding?.tvSpeacials?.setOnClickListener {
            findNavController().navigate(R.id.action_home_fragment_to_tv_speacial_fragment)
        }
        binding?.sourceOtt?.setOnClickListener {
            findNavController().navigate(R.id.action_home_fragment_to_sources_ott_fragment)
        }

    }

    override fun onResume() {
        super.onResume()
        CurrentPosition._positions.value = Positions.HOME
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}