package com.poplarhomes.browsehomes.ui.property

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.poplarhomes.browsehomes.databinding.FragmentAddPropertyBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import reactivecircus.flowbinding.android.view.clicks

class AddPropertyFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddPropertyBinding
    private val viewModel by viewModels<AddPropertyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddPropertyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        setViewCallbacks()
        setViewModel()
    }

    private fun setViews() {
    }

    private fun setViewCallbacks() {
        binding.buttonClose
            .clicks()
            .onEach {
                dismiss()
            }
            .launchIn(lifecycleScope)

        binding.buttonSubmit
            .clicks()
            .onEach {
            }
            .launchIn(lifecycleScope)
    }

    private fun setViewModel() {
        lifecycleScope.launchWhenResumed {
            viewModel.state.collect { state ->
                when (state) {
                    is AddPropertyState.ShowLoadingState -> {
                    }
                    is AddPropertyState.HideLoadingState -> {
                    }
                    is AddPropertyState.AddProperty -> {
                    }
                    is AddPropertyState.ShowErrorMessage -> {
                    }
                }
            }
        }
    }

}
