package com.punam.montra.di

import android.content.Context
import com.punam.montra.src.data.local_data.DataStoreDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideStoreDatabase(@ApplicationContext context: Context) =
        DataStoreDatabase(context = context)
}