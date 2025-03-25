package com.elewa.si_ware_task.core.di

import com.elewa.si_ware_task.BuildConfig
import com.elewa.si_ware_task.core.data.remote.AuthenticationInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Main Module to provide singleton on different classes used
 */
@Module
@InstallIn(SingletonComponent::class)
object  NetworkModule {

    @Singleton
    @Provides
    fun provideGson(): Gson =
        Gson()

    @Provides
    @Singleton
    fun providesLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun providesConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create( GsonBuilder().serializeNulls().create())
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        loggingInterceptor: Interceptor,
    ): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .hostnameVerifier { _, _ -> true }
            .addInterceptor(AuthenticationInterceptor())
        if (BuildConfig.DEBUG) { // debug ON
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }


    @Provides
    @Singleton
    fun providesRetrofit(
        client: OkHttpClient,
        factory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(factory)
            .client(client)
            .build()
    }
}