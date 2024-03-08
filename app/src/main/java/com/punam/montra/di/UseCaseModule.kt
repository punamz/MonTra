package com.punam.montra.di

import com.punam.montra.src.domain.repository.TransactionRepository
import com.punam.montra.src.domain.repository.UserRepository
import com.punam.montra.src.domain.use_case.transaction.CategoryGet
import com.punam.montra.src.domain.use_case.transaction.FrequencyGet
import com.punam.montra.src.domain.use_case.transaction.TransactionGet
import com.punam.montra.src.domain.use_case.transaction.TransactionUseCase
import com.punam.montra.src.domain.use_case.user.UserLogin
import com.punam.montra.src.domain.use_case.user.UserSignUp
import com.punam.montra.src.domain.use_case.user.UserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideUserUseCases(repository: UserRepository): UserUseCase {
        return UserUseCase(
            userLogin = UserLogin(repository),
            userSignUp = UserSignUp(repository),
        )
    }

    @Provides
    @Singleton
    fun providerTransactionUseCases(repository: TransactionRepository): TransactionUseCase {
        return TransactionUseCase(
            transactionGet = TransactionGet(repository),
            frequencyGet = FrequencyGet(repository),
            categoryGet = CategoryGet(repository)
        )
    }
}