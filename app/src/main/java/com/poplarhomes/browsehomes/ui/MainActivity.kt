package com.poplarhomes.browsehomes.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.poplarhomes.browsehomes.R
import com.poplarhomes.browsehomes.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import reactivecircus.flowbinding.android.view.clicks
import reactivecircus.flowbinding.android.widget.textChanges

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    private lateinit var adapter: BuildingsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViews()
        setViewCallbacks()
        setViewModel()
    }

    private fun setViews() {
        val items = resources.getStringArray(R.array.search_filters).toList()
        binding.listFilters.adapter = SearchFiltersAdapter(items)

        adapter = BuildingsAdapter()
        binding.listHomes.adapter = adapter
    }

    private fun setViewCallbacks() {
        binding.buttonMenu
            .clicks()
            .onEach {
            }
            .launchIn(lifecycleScope)

        binding.textSearch
            .textChanges()
            .skipInitialValue()
            .debounce(500)
            .onEach {
            }
            .launchIn(lifecycleScope)

        binding.buttonAdd
            .clicks()
            .onEach {
            }
            .launchIn(lifecycleScope)
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
                        adapter.setItems(state.buildings)
                    }
                    is MainState.ShowErrorMessage -> {
                    }
                }
            }
        }
    }

}
