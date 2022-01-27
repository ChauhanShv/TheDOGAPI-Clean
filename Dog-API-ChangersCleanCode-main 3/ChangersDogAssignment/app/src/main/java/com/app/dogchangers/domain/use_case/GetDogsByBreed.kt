package com.app.dogchangers.domain.use_case

import com.app.dogchangers.common.NetworkResult
import com.app.dogchangers.data.remote.dto.dogsbybreed.toDog
import com.app.dogchangers.domain.repository.DogsRepository
import com.app.dogchangers.domain.utils.ErrorMessage
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class GetDogsByBreed @Inject constructor(
    private val repository: DogsRepository
) {

    /**
     * Operator function to invoke dogs by breed
     */
    operator fun invoke(queryMap: HashMap<String, Any>) = flow {
        try {
            emit(NetworkResult.Loading())
            val result = repository.getDogsByBreed(queryMap).map { it.toDog() }
            Timber.e("result${result.size}")
            emit(NetworkResult.Success(result))
        } catch (e: HttpException) {
            emit(NetworkResult.Error(e.localizedMessage ?: ErrorMessage.UNKNOWN_ERROR))
        } catch (e: IOException) {
            emit(NetworkResult.Error(ErrorMessage.ERROR_CHECK_NETWORK_CONNECTION))
        }
    }

}