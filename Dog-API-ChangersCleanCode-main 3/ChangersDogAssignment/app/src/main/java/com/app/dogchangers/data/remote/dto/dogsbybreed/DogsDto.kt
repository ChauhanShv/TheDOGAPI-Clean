package com.app.dogchangers.data.remote.dto.dogsbybreed

import com.app.dogchangers.domain.models.dog_model.Dogs

data class DogsDto(
    val breeds: List<Breed>,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)fun DogsDto.toDog(): Dogs {
    return Dogs(
        dogImage = url
    )
}