package com.yapp.timecapsule.koin.repository

import okhttp3.OkHttpClient

interface RefreshClientRepository {
    fun getRefreshOkHttp(): OkHttpClient
}

interface AccessClientRepository {
    fun getAccessOkHttp(): OkHttpClient
}