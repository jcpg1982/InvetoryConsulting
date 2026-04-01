package pe.com.master.machines.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.com.master.machines.data.network.repository.ApiDataRepository
import pe.com.master.machines.data.firebase.repository.FirebaseDataRepository
import pe.com.master.machines.data.network.repositoryImpl.ApiDataRepositoryImpl
import pe.com.master.machines.data.firebase.repositoryImpl.FirebaseDataRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindApiDataRepository(impl: ApiDataRepositoryImpl): ApiDataRepository

    @Binds
    @Singleton
    abstract fun bindFirebaseDataRepository(impl: FirebaseDataRepositoryImpl): FirebaseDataRepository
}
