package com.rextor.movieapp.Details


import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide



import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.PackageManagerCompat

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.rextor.movieapp.LocalStrorage.Model.favouriteEntity
import com.rextor.movieapp.Model.TitleDetails
import com.rextor.movieapp.R
import com.rextor.movieapp.Utils.CurrentPosition
import com.rextor.movieapp.Utils.Positions
import com.rextor.movieapp.Utils.coroutineexceptionhandler
import com.rextor.movieapp.databinding.FragmentDetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class details_fragment : Fragment() {
    private var _binding : FragmentDetailsFragmentBinding? =null
    private val binding
        get() = _binding
    private var title:String? = null
    private var type : String? = null
    lateinit var viewModel: DetailViewModel
    lateinit var titleDetails: TitleDetails

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString("tille")
            type = it.getString("type")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        val view = binding?.root
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        CurrentPosition._positions.value = Positions.DETAILS
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getdetailsof(title!!)
        binding?.view?.visibility = View.INVISIBLE
        binding?.plotTitle?.visibility = View.INVISIBLE
        binding?.mediaPlay?.visibility = View.INVISIBLE
        binding?.favbutton?.visibility = View.INVISIBLE
        val coroutineexceptionhandler = coroutineexceptionhandler(requireContext())
        lifecycleScope.launch (coroutineexceptionhandler.coroutineExceptionHandler){
            viewModel.details.collectLatest {
                if (it != null) {
                    titleDetails =it
                   setbindings(titleDetails)
                }
            }
        }
        binding?.mediaPlay?.setOnClickListener {
            try {
                val intent = Intent().apply {
                    action = Intent.ACTION_VIEW
                    data = Uri.parse(titleDetails.trailer)
                }
                intent.putExtra("force_fullscreen", true)
                val packageManager = requireActivity().packageManager
                startActivity(intent)
            }catch (_:Exception){
                Toast.makeText(requireContext(), "No App to Play video", Toast.LENGTH_SHORT).show()
            }


//            if (intent.resolveActivity(packageManager)!=null){
//                startActivity(intent)
//            }else{
//                Toast.makeText(context, "No application to play the video", Toast.LENGTH_SHORT).show()
//            }
        }
        binding?.favbutton?.setOnClickListener {
            viewModel.insertfav(favouriteEntity(
                id = titleDetails.id,
                type = titleDetails.type,
                release_date = titleDetails.release_date,
                original_title = titleDetails.original_title
            ))
            Toast.makeText(context, "Added To Favorite", Toast.LENGTH_SHORT).show()
        }



    }

    private fun setbindings(titleDetails: TitleDetails) {
        binding?.favbutton?.visibility = View.VISIBLE
        binding?.progressBardetailScreen?.visibility  = View.INVISIBLE
        binding?.titleDetails?.text  = titleDetails.original_title
        Glide.with(requireContext()).load(titleDetails.backdrop).into(binding?.backdrop!!)
        Glide.with(requireContext()).load(titleDetails.poster).into(binding?.poster!!)
        binding?.view?.visibility = View.VISIBLE
        binding?.plotTitle?.visibility = View.VISIBLE
        binding?.plot?.text = titleDetails.plot_overview
        binding?.releaseDate?.text= getString(R.string.Releasedtae)+ titleDetails.release_date
        binding?.rating?.text= "Rating "+titleDetails.user_rating.toString()+"/10"
        binding?.mediaPlay?.visibility = View.VISIBLE
        Glide.with(requireContext()).load(titleDetails.trailer_thumbnail).into(binding?.tumbnailtrailer!!)
    }

    override fun onResume() {
        super.onResume()
        CurrentPosition._positions.value = Positions.DETAILS
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}