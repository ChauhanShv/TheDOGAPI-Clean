package com.app.dogchangers.data.repository

import com.app.dogchangers.data.remote.dto.breeds.BreedDto
import com.app.dogchangers.data.remote.dto.dogsbybreed.DogsDto
import com.app.dogchangers.data.remote.networkservice.RetrofitService
import com.app.dogchangers.domain.repository.DogsRepository
import javax.inject.Inject

class DogsRepositoryImp @Inject constructor(
    private val api: RetrofitService
) : DogsRepository {
    override suspend fun getBreeds(limit:Int): List<BreedDto> {
        return api.getBreeds(limit)
    }

    override suspend fun getDogsByBreed(queryMap: HashMap<String, Any> ): List<DogsDto> {
        return api.getDogsByBreed(queryMap)
    }

}