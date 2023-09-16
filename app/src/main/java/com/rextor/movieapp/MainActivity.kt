package com.rextor.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.rextor.movieapp.Utils.CurrentPosition
import com.rextor.movieapp.Utils.Positions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
   private var job: Job? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val coroutineScope = CoroutineScope(Dispatchers.Main)
        val header = findViewById<TextView>(R.id.WatchMode)
        val backPress = findViewById<ImageButton>(R.id.backPress)
        backPress.setOnClickListener {
//            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show()
            onBackPressed()
//            findNavController(
//                viewId = R.id.fragmentContainerView
//            ).navigateUp()
        }
        job= coroutineScope.launch {
            CurrentPosition.positions.collectLatest {
                when(it){
                    Positions.HOME-> {
                        header.text = getString(R.string.home)
                    }
                    Positions.DETAILS -> {
                        header.text = getString(R.string.details)
                    }
                    Positions.FAVOURITES -> {
                        header.text = getString(R.string.favourites)
                    }
                    Positions.MOVIES->{
                        header.text = getString(R.string.movies)
                    }
                    Positions.OTT->{
                        header.text = getString(R.string.source_ott)
                    }
                    Positions.TV_SHOWS->{
                        header.text = getString(R.string.tv_shows)
                    }
                    Positions.TV_SPEACIAL->{
                        header.text = getString(R.string.tv_special)
                    }
                    else -> {

                    }
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }

}