package com.app.dogchangers.presentation.main.viewmodels.state

import com.app.dogchangers.domain.models.breed_model.Breed

data class BreedsState(
    val isLoading: Boolean = false,
    val breedsList: List<Breed> = listOf(),
    val error: String = ""
)
