package com.yapp.timecapsule.koin.repository

import retrofit2.Retrofit

interface RefreshRetrofitRepository {
    fun getRefreshRetrofit(): Retrofit
}

interface AccessRetrofitRepository {
    fun getAccessRetrofit(): Retrofit
}