package com.app.dogchangers.presentation.main.viewmodels.state

import com.app.dogchangers.domain.models.dog_model.Dogs

data class DogsState(
    val isLoading: Boolean = false,
    val dogsList: List<Dogs> = emptyList(),
    val error: String = ""
)
