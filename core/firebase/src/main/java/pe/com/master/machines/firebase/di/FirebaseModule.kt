package pe.com.master.machines.firebase.di

import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.remoteConfig
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.com.master.machines.firebase.repository.RemoteConfigRepository
import pe.com.master.machines.firebase.repositoryImpl.RemoteConfigRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FirebaseModule {

    @Binds
    @Singleton
    abstract fun bindRemoteConfigRepository(impl: RemoteConfigRepositoryImpl): RemoteConfigRepository

    companion object {
        @Provides
        @Singleton
        fun provideFirebaseRemoteConfig(): FirebaseRemoteConfig {
            return Firebase.remoteConfig
        }
    }
}