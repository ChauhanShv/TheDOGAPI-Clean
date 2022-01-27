package com.app.dogchangers.domain.repository

import com.app.dogchangers.data.remote.dto.breeds.BreedDto
import com.app.dogchangers.data.remote.dto.dogsbybreed.DogsDto

interface DogsRepository {

    suspend fun getBreeds(limit: Int): List<BreedDto>

    suspend fun getDogsByBreed(queryMap: HashMap<String, Any>): List<DogsDto>

}