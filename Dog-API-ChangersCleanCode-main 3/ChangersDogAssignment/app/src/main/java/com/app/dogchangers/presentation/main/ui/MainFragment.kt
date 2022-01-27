package com.app.dogchangers.presentation.main.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.dogchangers.databinding.MainFragmentBinding
import com.app.dogchangers.domain.models.breed_model.Breed
import com.app.dogchangers.presentation.extensions.gone
import com.app.dogchangers.presentation.extensions.visible
import com.app.dogchangers.presentation.main.ui.adapter.BreedsAdapter
import com.app.dogchangers.presentation.main.ui.adapter.DogsAdapter
import com.app.dogchangers.presentation.main.viewmodels.MainViewModel
import com.app.dogchangers.presentation.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private var _binding: MainFragmentBinding by autoCleared()

    private var results : List<Breed> = emptyList()

    private lateinit var dogsAdapter : DogsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        viewModel.getBreeds()
        subscribeObserversBreeds()
        setAdapter()
        subscribeObserversDogs()
        // spinner onItemSelectedListener
        _binding.spBreedList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                //call to get selected breedId results
                callGetDogs(results[position].id)
                //clear old results
                dogsAdapter.setData(emptyList())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    /**
     * Get dogs by breedId
     */
    private fun callGetDogs(breedId: Int) {
        viewModel.getDogsByBreed(breedId)

    }

    /**
     * Set recycler list adapter
     */
    private fun setAdapter() {
        GridLayoutManager(
            activity, // context
            3, // span count
            RecyclerView.VERTICAL, // orientation
            false // reverse layout
        ).apply {
            // specify the layout manager for recycler view
            _binding.rvDogsList.layoutManager = this
        }
        dogsAdapter  = DogsAdapter()
        _binding.rvDogsList.adapter = dogsAdapter
    }


    /**
     * Subscribe to breeds observable, notified when the state of data object changes
     */
    private fun subscribeObserversBreeds() {
        viewModel.responseBreeds.observe(viewLifecycleOwner, { result ->
            if (result.isLoading) {
                Timber.e("loading")
                _binding.progressLoader.visible()
            } else if (result.breedsList.isNotEmpty()) {
                _binding.progressLoader.gone()
                results = result.breedsList
                _binding.spBreedList.adapter =
                    BreedsAdapter(requireContext(),android.R.layout.simple_spinner_item, values = result.breedsList)

                callGetDogs(result.breedsList[0].id)
            } else {
                _binding.progressLoader.gone()
                Timber.e("error")
            }
        })
    }


    /**
     * Subscribe to dogs observable, notified when the state of data object changes
     */
    private fun subscribeObserversDogs() {
        viewModel.responseDogs.observe(viewLifecycleOwner, { result ->
            if (result.isLoading) {
                Timber.e("loading")
                _binding.progressLoader.visible()
            } else if (result.dogsList.isNotEmpty()) {
                Timber.e("sizeUIList==>${result.dogsList.size}")
                _binding.rvDogsList.visible()
                _binding.progressLoader.gone()
                _binding.tvNoResults.gone()
                // finally, data bind the recycler view with adapter
                dogsAdapter.setData(result.dogsList)
            } else {
                Timber.e("error")
                _binding.rvDogsList.gone()
                _binding.progressLoader.gone()
                _binding.tvNoResults.visible()
            }
        })
    }

}