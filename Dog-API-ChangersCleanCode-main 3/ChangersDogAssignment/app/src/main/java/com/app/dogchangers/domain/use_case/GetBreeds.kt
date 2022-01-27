package com.app.dogchangers.domain.use_case

import com.app.dogchangers.common.NetworkResult
import com.app.dogchangers.data.remote.dto.breeds.BreedDto
import com.app.dogchangers.data.remote.dto.breeds.toBreed
import com.app.dogchangers.domain.models.breed_model.Breed
import com.app.dogchangers.domain.repository.DogsRepository
import com.app.dogchangers.domain.utils.Constants
import com.app.dogchangers.domain.utils.ErrorMessage.Companion.ERROR_CHECK_NETWORK_CONNECTION
import com.app.dogchangers.domain.utils.ErrorMessage.Companion.UNKNOWN_ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class GetBreeds @Inject constructor(
    private val repository: DogsRepository
) {

    /**
     * Operator function to invoke breeds
     */

   private val LIMIT = Constants.LIMIT_BREEDS

    operator fun invoke(): Flow<NetworkResult<List<Breed>>> = flow {
        try {
            emit(NetworkResult.Loading())
            val dataBreeds = repository.getBreeds(LIMIT).map { it.toBreed() }
            Timber.e("result${dataBreeds.size}")
            emit(NetworkResult.Success(dataBreeds))
        } catch (e: HttpException) {
            emit(NetworkResult.Error(e.localizedMessage ?: UNKNOWN_ERROR))
        } catch (e: IOException) {
            emit(NetworkResult.Error(ERROR_CHECK_NETWORK_CONNECTION))
        }
    }

}