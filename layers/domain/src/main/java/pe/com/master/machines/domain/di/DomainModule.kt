package pe.com.master.machines.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.com.master.machines.domain.network.usesCaseImpl.LoginUserUsesCaseImpl
import pe.com.master.machines.domain.network.usesCase.LoginUserUsesCase
import pe.com.master.machines.domain.network.usesCase.SearchActiveUsesCase
import pe.com.master.machines.domain.network.usesCaseImpl.SearchActiveUsesCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    @Singleton
    abstract fun bindLoginUserUsesCase(impl: LoginUserUsesCaseImpl): LoginUserUsesCase

    @Binds
    @Singleton
    abstract fun bindSearchActiveUsesCase(impl: SearchActiveUsesCaseImpl): SearchActiveUsesCase
}
