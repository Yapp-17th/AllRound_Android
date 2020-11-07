package com.yapp.timecapsule.koin.repositoryImpl

import com.yapp.timecapsule.BuildConfig
import com.yapp.timecapsule.consts.PrefConst
import com.yapp.timecapsule.koin.repository.AccessClientRepository
import com.yapp.timecapsule.koin.repository.RefreshClientRepository
import com.yapp.timecapsule.utils.SharedPreferenceHelper
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class RefreshClientRepositoryImpl: RefreshClientRepository {
    override fun getRefreshOkHttp(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain: Interceptor.Chain ->
            val refreshToken = SharedPreferenceHelper.getPrefsStringValue(PrefConst.REFRESH_TOKEN)
            var request = chain.request()
            if (refreshToken.isNotEmpty()) {
                request = request.newBuilder()
                    .method(request.method, request.body)
                    .addHeader("Authorization", "Bearer $refreshToken")
                    .build()
            }
            chain.proceed(request)
        }

        //log
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(loggingInterceptor)
        }

        // timeout
        httpClient.readTimeout(15, TimeUnit.SECONDS)
        httpClient.connectTimeout(15, TimeUnit.SECONDS)

        return httpClient.build()
    }
}

class AccessClientRepositoryImpl(
    // private val postRefreshTokenUseCase: GetRefreshTokenUseCase
): AccessClientRepository {
    override fun getAccessOkHttp(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain: Interceptor.Chain ->
            var accessToken = SharedPreferenceHelper.getPrefsStringValue(PrefConst.ACCESS_TOKEN)

//            val refreshTime = SharedPreferenceHelper.getPrefsLongValue(PrefConst.TOKEN_TIME_KEY)
//            val newTime = Date().time
//            if (newTime - refreshTime > 1200000) {
//                val response = postRefreshTokenUseCase.getRefreshToken().blockingGet()
//                accessToken = response.token
//                SharedPreferenceHelper.writePrefs(PrefConst.ACCESS_TOKEN, accessToken)
//                SharedPreferenceHelper.writePrefs(PrefConst.REFRESH_TOKEN, response.refreshToken)
//            }
            var request = chain.request()
            if (accessToken.isNotEmpty()) {
                request = request.newBuilder()
                    .method(request.method, request.body)
                    .addHeader("Authorization", "Bearer $accessToken")
                    .build()
            }
            return@addInterceptor chain.proceed(request)
        }

        //log
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(loggingInterceptor)
        }

        // timeout
        httpClient.readTimeout(15, TimeUnit.SECONDS)
        httpClient.connectTimeout(15, TimeUnit.SECONDS)

        return httpClient.build()
    }
}