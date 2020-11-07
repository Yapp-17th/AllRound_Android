package com.yapp.timecapsule.koin.module

import com.yapp.timecapsule.koin.repository.AccessClientRepository
import com.yapp.timecapsule.koin.repository.AccessRetrofitRepository
import com.yapp.timecapsule.koin.repository.RefreshClientRepository
import com.yapp.timecapsule.koin.repository.RefreshRetrofitRepository
import com.yapp.timecapsule.koin.repositoryImpl.AccessClientRepositoryImpl
import com.yapp.timecapsule.koin.repositoryImpl.AccessRetrofitRepositoryImpl
import com.yapp.timecapsule.koin.repositoryImpl.RefreshClientRepositoryImpl
import com.yapp.timecapsule.koin.repositoryImpl.RefreshRetrofitRepositoryImpl
import org.koin.dsl.module

val networkModule = module {
    single<RefreshClientRepository> { RefreshClientRepositoryImpl() }

    single<AccessClientRepository> { AccessClientRepositoryImpl() }

    single<RefreshRetrofitRepository> { RefreshRetrofitRepositoryImpl(get()) }

    single<AccessRetrofitRepository> { AccessRetrofitRepositoryImpl(get()) }
}