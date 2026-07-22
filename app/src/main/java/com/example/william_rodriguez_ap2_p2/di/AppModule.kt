package com.example.william_rodriguez_ap2_p2.di

import com.example.william_rodriguez_ap2_p2.data.remote.GastoApi
import com.example.william_rodriguez_ap2_p2.data.remote.gasto.GastoRemoteDataSource
import com.example.william_rodriguez_ap2_p2.data.remote.gasto.repository.GastoRepositoryImpl
import com.example.william_rodriguez_ap2_p2.domain.gasto.repository.GastoRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideGastoApi(moshi: Moshi): GastoApi {
        return Retrofit.Builder()
            .baseUrl("https://api-2026-h7eddqgydxc0fmau.eastus2-01.azurewebsites.net/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(GastoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGastoRemoteDataSource(gastoApi: GastoApi): GastoRemoteDataSource {
        return GastoRemoteDataSource(gastoApi)
    }

    @Provides
    @Singleton
    fun provideGastoRepository(
        remoteDataSource: GastoRemoteDataSource
    ): GastoRepository {
        return GastoRepositoryImpl(remoteDataSource)
    }
}