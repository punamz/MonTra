package com.punam.montra.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.punam.montra.src.data.local_data.DataStoreDatabase
import com.punam.montra.src.data.local_data.PreferencesKey
import com.punam.montra.src.data.remote.AuthenticationApi
import com.punam.montra.src.data.remote.TransactionApi
import com.punam.montra.util.AppHelper
import com.punam.montra.util.DateFormat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
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
    fun provideRetrofit(database: DataStoreDatabase): Retrofit {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)


        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor)
            .addInterceptor(Interceptor {
                val authenToken = runBlocking {
                    database.readValue(PreferencesKey.userToken, default = "").first()
                }
                val request: Request = it.request().newBuilder()
                    .addHeader("Authorization", "Bearer $authenToken")
                    .build()
                val response: Response = it.proceed(request)
                if (response.code == 401) {
                    runBlocking {
                        AppHelper().handleUnauthorized(database)
                    }

                }
                response
            })
            .build()
        return Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setDateFormat(DateFormat.iso8601)
                        .create()
                )
            )
            .client(client)
            .baseUrl("http://ec2-18-141-196-188.ap-southeast-1.compute.amazonaws.com/")
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthenticationApi(retrofit: Retrofit): AuthenticationApi {
        return retrofit
            .create(AuthenticationApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTransactionApi(retrofit: Retrofit): TransactionApi {
        return retrofit.create(TransactionApi::class.java)
    }
}