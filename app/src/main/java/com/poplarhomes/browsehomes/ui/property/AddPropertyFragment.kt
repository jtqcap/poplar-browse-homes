package com.poplarhomes.browsehomes.ui.property

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.poplarhomes.browsehomes.databinding.FragmentAddPropertyBinding
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import reactivecircus.flowbinding.android.view.clicks
import reactivecircus.flowbinding.android.widget.textChanges

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
        setViewCallbacks()
        setViewModel()
    }

    private fun setViewCallbacks() {
        binding.buttonClose
            .clicks()
            .onEach {
                dismiss()
            }
            .launchIn(lifecycleScope)

        binding.textAddress
            .textChanges()
            .skipInitialValue()
            .debounce(500)
            .onEach {
                viewModel.setAddress(it.toString())
            }
            .launchIn(lifecycleScope)

        binding.textBedrooms
            .textChanges()
            .skipInitialValue()
            .debounce(500)
            .onEach {
                viewModel.setBedrooms(
                    if (it.isBlank()) null
                    else it.toString().toInt()
                )
            }
            .launchIn(lifecycleScope)

        binding.textBathrooms
            .textChanges()
            .skipInitialValue()
            .debounce(500)
            .onEach {
                viewModel.setBathrooms(
                    if (it.isBlank()) null
                    else it.toString().toInt()
                )
            }
            .launchIn(lifecycleScope)

        binding.textRent
            .textChanges()
            .skipInitialValue()
            .debounce(500)
            .onEach {
                viewModel.setRent(
                    if (it.isBlank()) null
                    else it.toString().toDouble()
                )
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
                    is AddPropertyState.SetSubmitButton -> {
                        binding.buttonSubmit.isEnabled = state.isComplete
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
