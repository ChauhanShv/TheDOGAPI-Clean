package com.app.dogchangers.data.remote.dto.breeds

import com.app.dogchangers.domain.models.breed_model.Breed

data class BreedDto(
    val bred_for: String,
    val breed_group: String,
    val country_code: String,
    val description: String,
    val height: Height,
    val history: String,
    val id: Int,
    val image: Image,
    val life_span: String,
    val name: String,
    val origin: String,
    val reference_image_id: String,
    val temperament: String,
    val weight: Weight
)
fun BreedDto.toBreed(): Breed {
    return Breed(
        id = id,
        breedName = name
    )
}