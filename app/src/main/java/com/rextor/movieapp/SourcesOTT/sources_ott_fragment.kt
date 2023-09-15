package com.rextor.movieapp.SourcesOTT

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.rextor.movieapp.CommonAdaptor.Adaptor
import com.rextor.movieapp.Model.Type
import com.rextor.movieapp.R
import com.rextor.movieapp.Utils.CurrentPosition
import com.rextor.movieapp.Utils.Positions
import com.rextor.movieapp.Utils.coroutineexceptionhandler
import com.rextor.movieapp.databinding.FragmentSourcesOttFragmentBinding
import com.rextor.movieapp.databinding.FragmentTvShowsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class sources_ott_fragment : Fragment() {

    private var _binding : FragmentSourcesOttFragmentBinding?= null
    val binding
        get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CurrentPosition._positions.value = Positions.OTT
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentSourcesOttFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        val view = binding?.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this).get(SourceOttviewmodel::class.java)
        val adaptor = SourceOTTAdaptor(requireContext()){url->
            try {
                val intent = Intent().apply {
                    action = Intent.ACTION_VIEW
                    data = Uri.parse(url)
                }
                startActivity(intent)
            }catch (_:Exception){
                Toast.makeText(requireContext(), "No Url present", Toast.LENGTH_SHORT).show()
            }

        }
        val coroutineexceptionhandler = coroutineexceptionhandler(requireContext())
        binding?.OTTlist?.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = adaptor
        }
        lifecycleScope.launch(coroutineexceptionhandler.coroutineExceptionHandler) {
            viewModel.listofott.collectLatest {
                if (it.isNotEmpty()){
                    binding?.progressBarOTT?.visibility  = View.INVISIBLE
                    adaptor.submitList(it)
                }

            }
        }
    }

    override fun onResume() {
        super.onResume()
        CurrentPosition._positions.value = Positions.OTT
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}