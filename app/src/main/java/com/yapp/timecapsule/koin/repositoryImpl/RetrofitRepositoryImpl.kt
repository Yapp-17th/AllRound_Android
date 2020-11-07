package com.yapp.timecapsule.koin.repositoryImpl

import android.annotation.SuppressLint
import com.yapp.timecapsule.BuildConfig
import com.yapp.timecapsule.consts.Const
import com.yapp.timecapsule.consts.UrlConst
import com.yapp.timecapsule.koin.repository.AccessClientRepository
import com.yapp.timecapsule.koin.repository.AccessRetrofitRepository
import com.yapp.timecapsule.koin.repository.RefreshClientRepository
import com.yapp.timecapsule.koin.repository.RefreshRetrofitRepository
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RefreshRetrofitRepositoryImpl(private val okHttpRepo: RefreshClientRepository) :
    RefreshRetrofitRepository {
    @SuppressLint("DefaultLocale")
    override fun getRefreshRetrofit(): Retrofit {
        val client = okHttpRepo.getRefreshOkHttp()
        val baseUrl: String = if (BuildConfig.Mode.toLowerCase().contains(Const.APP_STAGING))
            UrlConst.PROD_URL else UrlConst.DEV_URL
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }
}

class AccessRetrofitRepositoryImpl(private val okHttpRepo: AccessClientRepository) :
    AccessRetrofitRepository {
    @SuppressLint("DefaultLocale")
    override fun getAccessRetrofit(): Retrofit {
        val client = okHttpRepo.getAccessOkHttp()
        val baseUrl: String = if (BuildConfig.Mode.toLowerCase().contains(Const.APP_STAGING))
            UrlConst.PROD_URL else UrlConst.DEV_URL
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }
}