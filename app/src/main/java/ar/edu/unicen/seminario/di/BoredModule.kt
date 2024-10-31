package ar.edu.unicen.seminario.di

import ar.edu.unicen.seminario.ddl.data.BoredApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class BoredModule {

    @Provides
    fun providesRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://bored.api.lewagon.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideBoredApi(
        retrofit: Retrofit
    ): BoredApi {
        return retrofit.create(BoredApi::class.java)
    }

}