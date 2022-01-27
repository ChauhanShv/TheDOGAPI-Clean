package com.app.dogchangers.di.repository

import com.app.dogchangers.data.remote.networkservice.RetrofitService
import com.app.dogchangers.data.repository.DogsRepositoryImp
import com.app.dogchangers.domain.repository.DogsRepository
import com.app.dogchangers.domain.use_case.GetBreeds
import com.app.dogchangers.domain.use_case.GetDogsByBreed
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DogsRepositoryModule{

    //Messages repo DI
    @Singleton
    @Provides
    fun provideDogsRepository( api: RetrofitService
    ): DogsRepository {
        return DogsRepositoryImp(api)
    }

    @Singleton
    @Provides
    fun provideGetBreeds( dogsRepository: DogsRepository
    ): GetBreeds {
        return GetBreeds(dogsRepository)
    }

    @Singleton
    @Provides
    fun provideGetDogsByBreed(dogsRepository: DogsRepository
    ): GetDogsByBreed {
        return GetDogsByBreed(dogsRepository)
    }

}