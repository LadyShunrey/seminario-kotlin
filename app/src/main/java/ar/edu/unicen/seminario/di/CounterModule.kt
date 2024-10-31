package ar.edu.unicen.seminario.di

import android.content.Context
import android.content.SharedPreferences
import ar.edu.unicen.seminario.ddl.CounterLocalDataSource
import ar.edu.unicen.seminario.ddl.CounterRepository
import ar.edu.unicen.seminario.ui.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CounterModule {

    @Provides
    @CounterSharedPreference
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext
        context: Context
    ): SharedPreferences {
        return context.getSharedPreferences("counter", Context.MODE_PRIVATE)
    }

//    @Provides
//    fun provideLocalDataSource(
//        @CounterSharedPreference
//        sharedPreferences: SharedPreferences
//    ): CounterLocalDataSource {
//        return CounterLocalDataSource(sharedPreferences)
//    }

//    @Provides
//    fun provideRepository(
//        localDataSource: CounterLocalDataSource
//    ): CounterRepository {
//        return CounterRepository(localDataSource)
//    }

}