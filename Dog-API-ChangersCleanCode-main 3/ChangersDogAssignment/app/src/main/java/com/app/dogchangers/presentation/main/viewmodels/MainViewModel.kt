package com.app.dogchangers.presentation.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.dogchangers.common.NetworkResult
import com.app.dogchangers.domain.use_case.GetBreeds
import com.app.dogchangers.domain.use_case.GetDogsByBreed
import com.app.dogchangers.domain.utils.Constants
import com.app.dogchangers.domain.utils.ErrorMessage
import com.app.dogchangers.presentation.main.viewmodels.state.BreedsState
import com.app.dogchangers.presentation.main.viewmodels.state.DogsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel@Inject constructor(
   private val getBreeds: GetBreeds,
   private val getDogsByBreed: GetDogsByBreed)
    : ViewModel() {

    //observable for breeds
    private val _responseBreeds: MutableLiveData<BreedsState> = MutableLiveData()

    val responseBreeds: LiveData<BreedsState> = _responseBreeds


    //observable for dogs
    private val _responseDogs: MutableLiveData<DogsState> = MutableLiveData()

    val responseDogs: LiveData<DogsState> = _responseDogs


    /**
     * Function to get breeds
     */

    fun getBreeds() {
        getBreeds.invoke().onEach { result ->
            when (result) {
                is NetworkResult.Success -> {
                    Timber.e("Success$result")
                        _responseBreeds.value = result.data?.let { BreedsState(breedsList = it) }
                }
                is NetworkResult.Error -> {
                    Timber.e("Error")
                    _responseBreeds.value = BreedsState(
                        error = result.message ?: ErrorMessage.UNKNOWN_ERROR
                    )
                }
                is NetworkResult.Loading -> {
                    Timber.e("Loading")
                    _responseBreeds.value = BreedsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


    /**
     * Function to get breeds
     */

    fun getDogsByBreed(breedId: Int) {
        getDogsByBreed.invoke(queryMap(0,breedId)).onEach { result ->

            when (result) {
                is NetworkResult.Success -> {
                    Timber.e("Success$result")
                    _responseDogs.value = result.data?.let { DogsState(dogsList = it) }
                }
                is NetworkResult.Error -> {
                    Timber.e("Error")
                    _responseDogs.value = DogsState(
                        error = result.message ?: ErrorMessage.UNKNOWN_ERROR
                    )
                }
                is NetworkResult.Loading -> {
                    Timber.e("Loading")
                    _responseDogs.value = DogsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


    /**
     * Create queryMap to get Images for dogs
     */
    private fun  queryMap( page: Int, breedId: Int) : HashMap<String, Any> {
        var queryMap: HashMap<String, Any> = HashMap()
        queryMap["limit"] = Constants.LIMIT
        queryMap["page"] = page
        queryMap["breed_ids"] = breedId
        queryMap["order"] = "Asc"
        queryMap["size"] = "small"
        Timber.e("params$queryMap")
        return queryMap
    }
}