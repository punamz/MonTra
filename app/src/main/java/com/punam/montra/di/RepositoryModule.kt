package com.punam.montra.di

import com.punam.montra.src.data.remote.AuthenticationApi
import com.punam.montra.src.data.repository.UserRepositoryImpl
import com.punam.montra.src.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideUserRepository(authenticationApi: AuthenticationApi): UserRepository {
        return UserRepositoryImpl(
             api = authenticationApi
        )
    }
}