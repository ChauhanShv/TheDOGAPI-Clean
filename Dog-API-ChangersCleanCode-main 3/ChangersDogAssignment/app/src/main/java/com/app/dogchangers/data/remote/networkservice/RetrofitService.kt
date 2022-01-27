package com.app.dogchangers.data.remote.networkservice

import com.app.dogchangers.data.remote.dto.breeds.BreedDto
import com.app.dogchangers.data.remote.dto.dogsbybreed.DogsDto
import retrofit2.http.*

interface RetrofitService {

    @GET("breeds")
    suspend fun getBreeds(@Query("limit") limit: Int
    ): List<BreedDto>

    @GET("images/search")
    suspend fun getDogsByBreed(
        @QueryMap map: HashMap<String,Any>
    ): List<DogsDto>
}
