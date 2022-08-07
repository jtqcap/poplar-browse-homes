package com.poplarhomes.browsehomes.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.poplarhomes.browsehomes.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViews()
        setViewModel()
    }

    private fun setViews() {
    }

    private fun setViewModel() {
        lifecycleScope.launchWhenResumed {
            viewModel.state.collect { state ->
                when (state) {
                    is MainState.ShowLoadingState -> {
                    }
                    is MainState.HideLoadingState -> {
                    }
                    is MainState.GetBuildings -> {
                    }
                    is MainState.ShowErrorMessage -> {
                    }
                }
            }
        }
    }

}
