package com.punam.montra.di

import android.content.Context
import com.punam.montra.src.data.local_data.DataStoreDatabase
import com.punam.montra.src.data.remote.AuthenticationApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideStoreDatabase(@ApplicationContext context: Context) =
        DataStoreDatabase(context = context)


    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl("http://restapi.adequateshop.com/api/")
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthenticationApi(retrofit: Retrofit ) : AuthenticationApi {
        return retrofit
            .create(AuthenticationApi::class.java)
    }
}