package br.com.myrepositories.core.di

import br.com.myrepositories.data.BASE_URL
import br.com.myrepositories.data.MyRepositoryImpl
import br.com.myrepositories.data.api.MyRepositoriesApi
import br.com.myrepositories.domain.repository.MyRepository
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
object MyRepositoriesModule {

    @Singleton
    @Provides
    fun createService(): MyRepositoriesApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyRepositoriesApi::class.java)
    }

    private fun createHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addNetworkInterceptor(httpLoggingInterceptor)
            .connectTimeout(1, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRepository(myRepositoriesApi: MyRepositoriesApi):MyRepository = MyRepositoryImpl(myRepositoriesApi)

}