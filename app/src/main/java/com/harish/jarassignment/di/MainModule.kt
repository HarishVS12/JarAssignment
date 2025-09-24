package com.harish.jarassignment.di

import com.harish.jarassignment.BuildConfig
import com.harish.jarassignment.data.remote.MainApiInterface
import com.harish.jarassignment.data.repo.MainRepo
import com.harish.jarassignment.domain.repo.MainRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object MainModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {

        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().apply {

            this.addInterceptor {
                val request = it.request()
                    .newBuilder()
                    .build()
                it.proceed(request)
            }

            //Add Logging
            this.addInterceptor(interceptor)
                .connectTimeout(3, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(25, TimeUnit.SECONDS)

        }.build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun providesMainApiInterface(retrofit: Retrofit): MainApiInterface {
        return retrofit.create(MainApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun providesMainRepoImpl(mainApiInterface: MainApiInterface): MainRepo {
        return MainRepoImpl(mainApiInterface)
    }

}